package com.baomidou.mybatisplus.samples.crud.task;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.samples.crud.spider.entity.Stock;
import com.baomidou.mybatisplus.samples.crud.spider.entity.StockAnalysis;
import com.baomidou.mybatisplus.samples.crud.spider.entity.StockPrice;
import com.baomidou.mybatisplus.samples.crud.spider.service.IStockAnalysisService;
import com.baomidou.mybatisplus.samples.crud.spider.service.IStockPriceService;
import com.baomidou.mybatisplus.samples.crud.spider.service.IStockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.baomidou.mybatisplus.samples.crud.constants.Constants.DEFAULT_CURRENT_PAGE;
import static com.baomidou.mybatisplus.samples.crud.constants.Constants.DEFAULT_PAGE_SIZE;

/**
 * @author joking
 */
@Slf4j
@Service
public class AutoComputeTask {

    @Value("${task.auto-compute.days}")
    private Integer[] days;

    private final IStockService stockService;
    private final IStockPriceService stockPriceService;
    private final IStockAnalysisService stockAnalysisService;

    public AutoComputeTask(IStockService stockService, IStockPriceService stockPriceService, IStockAnalysisService stockAnalysisService) {
        this.stockService = stockService;
        this.stockPriceService = stockPriceService;
        this.stockAnalysisService = stockAnalysisService;
    }

    public void start() {
        log.info("自动计算任务【{}】开始！", this.getClass().getSimpleName());
        long start = System.currentTimeMillis();
        int currentPage = DEFAULT_CURRENT_PAGE;
        while(true) {
            Page<Stock> page = new Page<>(currentPage, DEFAULT_PAGE_SIZE);
            IPage<Stock> resultPage = stockService.lambdaQuery()
                    .notLike(Stock::getComputeTime, LocalDate.now().toString())
                    .orderByAsc(Stock::getId)
                    .page(page);
            List<Stock> list = resultPage.getRecords();
            if (CollectionUtils.isNotEmpty(list)) {
                list.forEach(stock -> {
                    try {
                        computeStockPrice(stock);
                        log.info("处理完成，线程休息。。。");
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

    private void computeStockPrice(Stock stock) {
        if(days == null || days.length < 1) {
            days = new Integer[]{7,30,180};
        }
        int maxDay = this.getMaxDay(days);
        List<StockPrice> list = stockPriceService.lambdaQuery()
                .eq(StockPrice::getStockId, stock.getId())
                .orderByDesc(StockPrice::getPriceDate)
                .last("limit " + maxDay)
                .list();
        if(CollectionUtils.isNotEmpty(list)) {
            boolean needCompute = assertDataCompete(list);
            if(needCompute) {
                List<StockAnalysis> analyses = new ArrayList<>();
                StockPrice current = list.get(0);
                for (Integer day : days) {
                    try {
                        StockPrice stockPrice = list.get(day - 1);
                        StockAnalysis analysis = new StockAnalysis();
                        analysis.setStockId(stock.getId());
                        analysis.setStockCode(stock.getCode());
                        analysis.setLateDays(day);
                        analysis.setUpdateTime(ZonedDateTime.now().toLocalDateTime());
                        // 涨幅
                        float gain = current.getClosePrice() - stockPrice.getClosePrice();
                        float gainRate = gain / stockPrice.getClosePrice();
                        analysis.setGain((double) gain);
                        analysis.setGainRate((double)gainRate);

                        analyses.add(analysis);
                    } catch (Exception e) {
                        log.error("计算【{}】天分析出错：{}", day, e.getMessage());
                    }
                }
                // 插入数据库
                if(!analyses.isEmpty()) {
                    log.info("插入数据【{}】条。", analyses.size());
                    stockAnalysisService.saveBatch(analyses);
                    // 更新 计算时间
                    stock.setComputeTime(ZonedDateTime.now().toLocalDateTime());
                    stockService.updateById(stock);
                }
            }
        }
    }

    /**
     * 判断是否需要计算，规则是，抓取的数据到最近三天就计算，否则不计算
     * @param list      价格数据
     * @return  boolean
     */
    private boolean assertDataCompete(List<StockPrice> list) {
        if(CollectionUtils.isNotEmpty(list)) {
            StockPrice stockPrice = list.get(0);
            return stockPrice.getPriceDate().isAfter(LocalDate.now().minusDays(4));
        }
        return false;
    }

    private int getMaxDay(Integer[] days) {
        int max = 0;
        int count = 0;
        if(days != null && days.length > 1) {
            while(count < days.length) {
                max = Math.max(max, days[count++]);
            }
        }
        return max;
    }
}
