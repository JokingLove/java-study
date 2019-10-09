package com.baomidou.mybatisplus.samples.crud.task;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.samples.crud.model.SinaRealTimeResult;
import com.baomidou.mybatisplus.samples.crud.spider.entity.Stock;
import com.baomidou.mybatisplus.samples.crud.spider.entity.StockPrice;
import com.baomidou.mybatisplus.samples.crud.spider.service.IStockPriceService;
import com.baomidou.mybatisplus.samples.crud.spider.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static com.baomidou.mybatisplus.samples.crud.constants.Constants.DEFAULT_CURRENT_PAGE;
import static com.baomidou.mybatisplus.samples.crud.constants.Constants.DEFAULT_PAGE_SIZE;
import static java.util.stream.Collectors.joining;

/**
 * @author joking
 */
@Slf4j
@Service
public class SinaRealTimeStockPriceTask implements ApplicationRunner {

    private final static Integer PRE_REQUEST_COUNT = 30;
    private final static String BASE_URL = "http://hq.sinajs.cn/list=";

    private final IStockService stockService;
    private final IStockPriceService stockPriceService;
    private final RestTemplate restTemplate;

    public SinaRealTimeStockPriceTask(IStockService stockService, IStockPriceService stockPriceService, RestTemplate restTemplate) {
        this.stockService = stockService;
        this.stockPriceService = stockPriceService;
        this.restTemplate = restTemplate;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        start();
    }

    public void start() {
        // 分页查询
        int currentPage = DEFAULT_CURRENT_PAGE;
        long startTime = System.currentTimeMillis();
        while(true) {
            try {
                Page<Stock> page = new Page<>(currentPage, DEFAULT_PAGE_SIZE);
                IPage<Stock> resultPage = stockService.lambdaQuery()
                        .page(page);
                List<Stock> stocks = resultPage.getRecords();
                if(CollectionUtils.isNotEmpty(stocks)) {
                    int count = 1;
                    int size = stocks.size();
                    int total = (size % PRE_REQUEST_COUNT == 0) ? size / PRE_REQUEST_COUNT : size / PRE_REQUEST_COUNT + 1;
                    int from = PRE_REQUEST_COUNT * (count -1);
                    int to = PRE_REQUEST_COUNT * count;
                    to = Math.min(to, size);
                    while(count <= total) {
                        List<Stock> subList = stocks.subList(from, to);
                        try {
                            handleSubList(subList);
                        }catch (Exception e) {
                            log.error("处理数据【{}-{}】出错：{}", from, to, e.getMessage());
                        }
                        count ++;
                    }
                } else {
                    log.info("查询数据为空，程序正常退出。。。");
                    break;
                }
            }catch (Exception e) {
                log.error("出错了： {}", e.getMessage(), e);
            }
            currentPage++;
        }
        long timeMillis = System.currentTimeMillis() - startTime;
        log.info("任务[{}]处理完成，耗时：{}s", this.getClass().getSimpleName(), timeMillis / 1000);
    }

    private void handleSubList(List<Stock> subList) {
        if(CollectionUtils.isNotEmpty(subList)) {
            String params = subList.stream().map(Stock::getSymbol)
                    .collect(joining(","));
            String url = BASE_URL + params;
            String message = restTemplate.getForObject(url, String.class);
            SinaRealTimeResult sinaRealTimeResult = new SinaRealTimeResult(message);
            List<List<String>> result = sinaRealTimeResult.getResult();
            if(CollectionUtils.isNotEmpty(result)) {
                result.forEach(this::handleStockPrice);
            }
        }
    }

    private void handleStockPrice(List<String> strings) {
        StockPrice stockPrice = SinaRealTimeResult.buildStockPrice(strings);
        Integer count = stockPriceService.lambdaQuery()
                .eq(StockPrice::getPriceDate, stockPrice.getPriceDate())
                .eq(StockPrice::getStockCode, stockPrice.getStockCode())
                .count();
        if(count != null && count > 0) {
            log.info("更新数据：{}", stockPrice);
            stockPriceService.lambdaUpdate()
                    .eq(StockPrice::getPriceDate, stockPrice.getPriceDate())
                    .eq(StockPrice::getStockCode, stockPrice.getStockCode())
                    .update(stockPrice);
        } else {
            log.info("插入数据：{}", stockPrice);
            stockPriceService.save(stockPrice);
        }
    }

}
