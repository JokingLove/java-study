package com.baomidou.mybatisplus.samples.crud.config;

import com.baomidou.mybatisplus.samples.crud.task.SinaRealTimeStockPriceTask;
import com.baomidou.mybatisplus.samples.crud.task.SohuStockPriceTask;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author joking
 */
@Configuration
@EnableScheduling
public class SpiderScheduleConfig {

    private final SohuStockPriceTask sohuStockPriceTask;
    private final SinaRealTimeStockPriceTask sinaRealTimeStockPriceTask;

    public SpiderScheduleConfig(SohuStockPriceTask sohuStockPriceTask, SinaRealTimeStockPriceTask sinaRealTimeStockPriceTask) {
        this.sohuStockPriceTask = sohuStockPriceTask;
        this.sinaRealTimeStockPriceTask = sinaRealTimeStockPriceTask;
    }


    @Scheduled(cron = "${cron.spider.sohu-task}")
    public void spiderSohuStockData() {
        sohuStockPriceTask.start();
    }

    @Scheduled(cron = "${cron.spider.sina-real-time-task}")
    public void spiderSinaRealTimePriceTask() {
        sinaRealTimeStockPriceTask.start();
    }
}
