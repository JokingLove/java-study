package com.baomidou.mybatisplus.samples.crud.task;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.samples.crud.model.SohuResult;
import com.baomidou.mybatisplus.samples.crud.spider.entity.Stock;
import com.baomidou.mybatisplus.samples.crud.spider.entity.StockPrice;
import com.baomidou.mybatisplus.samples.crud.spider.service.IStockPriceService;
import com.baomidou.mybatisplus.samples.crud.spider.service.IStockService;
import com.baomidou.mybatisplus.samples.crud.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static com.baomidou.mybatisplus.samples.crud.constants.Constants.DEFAULT_CURRENT_PAGE;
import static com.baomidou.mybatisplus.samples.crud.constants.Constants.DEFAULT_PAGE_SIZE;

/**
 * @author joking
 */
@Slf4j
@Service
public class SohuStockPriceTask {

    /**
     * # "2011-06-30", 时间
     * # "10.15",      开盘价
     * # "10.02",      收盘价
     * # "3.14",       涨跌额
     * # "45.64%",     涨跌幅
     * # "9.40",       最低
     * # "10.58",      最高
     * # "733820",     成交量
     * # "72940.49",   成交额
     * # "93.60%"      换手率
     */

    private static final int PRE_SAVE_COUNT = 500;
    /**
     * start=19910422&end=20190930&
     */
    private static final String BASE_URL = "http://q.stock.sohu.com/hisHq?stat=1&order=A&code=cn_";
    private final IStockPriceService stockPriceService;
    private final IStockService stockService;
    private final RestTemplate restTemplate;
    private final ExecutorService threadPool;

    @Value("${spider.sohu-task.batch}")
    private boolean batch;

    public SohuStockPriceTask(IStockPriceService stockPriceService, IStockService stockService, RestTemplate restTemplate, ExecutorService threadPool) {
        this.stockPriceService = stockPriceService;
        this.stockService = stockService;
        this.restTemplate = restTemplate;
        this.threadPool = threadPool;
    }

    public void start() {
        log.info("任务【{}】开始！", this.getClass().getSimpleName());
        long start = System.currentTimeMillis();
        int currentPage = DEFAULT_CURRENT_PAGE;
        while(true) {
            Page<Stock> page = new Page<>(currentPage, DEFAULT_PAGE_SIZE);
            IPage<Stock> resultPage = stockService.lambdaQuery()
                    .orderByAsc(Stock::getId)
                    .page(page);
            List<Stock> list = resultPage.getRecords();
            if (CollectionUtils.isNotEmpty(list)) {
                list.forEach(stock -> {
                    try {
                        downloadAndResolve(stock);
                        log.info("处理完成，线程休息。。。");
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        log.error("出错了：{}", e.getMessage());
                    }
                });
            } else {
                log.info("查询数据为空，程序退出！");
                break;
            }
            currentPage ++;
        }
        long time = System.currentTimeMillis() - start;
        log.info("处理完成，正常退出！耗时：{}s", time);
    }


    private void downloadAndResolve(Stock stock) {
        String preUrl = resolveUrl(stock);
        if(preUrl != null) {
            log.info("开始处理数据：{}", preUrl);
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(preUrl, String.class);
            String resultStr = responseEntity.getBody();
            if (StringUtils.isNotEmpty(resultStr)) {
                List<SohuResult> sohuResults = JSON.parseArray(resultStr, SohuResult.class);
                if (CollectionUtils.isNotEmpty(sohuResults)) {
                    List<StockPrice> stockPrices = resolveStockPrices(sohuResults, stock);
                    // 插入数据库
                    if (CollectionUtils.isNotEmpty(stockPrices)) {
                        if(batch) {
                            threadPool.execute(() -> this.asyncBatchSave(stockPrices, stock));
                        } else {
                            // 同步操作
                            threadPool.execute(() -> this.updateOrSaveStockPrices(stockPrices, stock));
                        }
                    }
                }
            }
        }
    }

    private void updateOrSaveStockPrices(List<StockPrice> stockPrices, Stock stock) {
        if(CollectionUtils.isNotEmpty(stockPrices)) {
            stockPrices.forEach(stockPrice -> {
                StockPrice dbStockPrice = stockPriceService.lambdaQuery()
                        .eq(StockPrice::getPriceDate, stockPrice.getPriceDate())
                        .eq(StockPrice::getStockCode, stockPrice.getStockCode())
                        .last("limit 1")
                        .one();
                if(dbStockPrice != null) {
                    // 更新
                    log.info("已经存在 【{}-{}】,更新！", stockPrice.getStockCode(), stockPrice.getPriceDate());
                    stockPrice.setId(dbStockPrice.getId());
                    stockPriceService.updateById(stockPrice);
                } else {
                    log.info("数据不存在【{}-{}】,插入！", stockPrice.getStockCode(), stockPrice.getPriceDate());
                    stockPriceService.save(stockPrice);
                }
            });
            this.updateStockSpiderTime(stock);
        }
    }

    private String resolveUrl(Stock stock) {
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL).append(stock.getCode());
        Page page = new Page();
        page.setSize(1);
        page.setCurrent(1);
        StockPrice one = stockPriceService.lambdaQuery()
                .eq(StockPrice::getStockCode, stock.getCode())
                .orderByDesc(StockPrice::getPriceDate)
                .last("limit 1")
                .one();
        final DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd");
        if (one != null) {
            if(one.getPriceDate().equals(LocalDate.now())) {
                // 如果最新的一条数据就是今天的，那就不抓了
                log.info("已经是最新的数据，不需要抓取：{}", one);
                return null;
            }
            // start=19910422&end=20190930&
            builder.append("&start=").append(one.getPriceDate().minusDays(3).format(yyyyMMdd))
                    .append("&end=").append(LocalDate.now().format(yyyyMMdd));
        } else {
            builder.append("&start=").append(LocalDate.now().minusWeeks(1).format(yyyyMMdd))
                    .append("&end=").append(LocalDate.now().format(yyyyMMdd));
        }
        return builder.toString();
    }

    private void asyncBatchSave(List<StockPrice> stockPrices, Stock stock) {
        int size = stockPrices.size();
        if (size >= PRE_SAVE_COUNT) {
            boolean flag = size % PRE_SAVE_COUNT == 0;
            int count = flag ? size / PRE_SAVE_COUNT : size / PRE_SAVE_COUNT + 1;
            for (int i = 0; i < count; i++) {
                int from = i * PRE_SAVE_COUNT;
                int to = PRE_SAVE_COUNT * (i + 1);
                to = Math.min(to, size);
                log.info("批量插入数据：[{}] -- {} - {}", size, from, to);
                boolean b = stockPriceService.saveBatch(stockPrices.subList(from, to));
                log.info("批量更新结果: {}", b);
            }
        } else {
            log.info("一次性插入数据：【{}】条。", size);
            boolean b = stockPriceService.saveBatch(stockPrices);
            log.info("批量更新结果: {}", b);
        }
        this.updateStockSpiderTime(stock);
    }

    private void updateStockSpiderTime(Stock stock) {
        boolean update = stockService.lambdaUpdate()
                .eq(Stock::getId, stock.getId())
                .set(Stock::getSpiderTime, ZonedDateTime.now().toLocalDateTime())
                .update();
        log.info("更新 stock-spider_time【{}】结果: {}", stock.getId(), update);
    }

    /**
     * 从结果中解析出需要的对象
     */
    private List<StockPrice> resolveStockPrices(List<SohuResult> sohuResults, Stock stock) {
        SohuResult sohuResult = sohuResults.get(0);
        if (sohuResult != null) {
            return Arrays.stream(sohuResult.getHq())
                    .map(item -> {
                        StockPrice price = new StockPrice();
                        price.setStockId(stock.getId());
                        price.setStockCode(stock.getCode());
                        price.setPriceDate(StringUtil.stringToLocalDate(item[0]));
                        price.setOpenPrice(StringUtil.stringToFloat(item[1]));
                        price.setClosePrice(StringUtil.stringToFloat(item[2]));
                        price.setDiffPrice(StringUtil.stringToFloat(item[3]));
                        price.setDiffRangeStr(item[4]);
                        price.setDiffRange(StringUtil.transPercentToFloat(item[4]));
                        price.setLowstPrice(StringUtil.stringToFloat(item[5]));
                        price.setHighstPrice(StringUtil.stringToFloat(item[6]));
                        price.setVolume(StringUtil.stringToBigDecimal(item[7]));
                        price.setTurnover(StringUtil.stringToBigDecimal(item[8]));
                        price.setTurnoverRateStr(item[9]);
                        price.setTurnoverRate(StringUtil.transPercentToFloat(item[9]));
                        price.setUpdateTime(ZonedDateTime.now().toLocalDateTime());
                        return price;
                    }).collect(Collectors.toList());
        }
        return null;
    }

}


