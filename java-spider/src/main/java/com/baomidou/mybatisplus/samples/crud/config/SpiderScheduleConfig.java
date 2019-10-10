package com.baomidou.mybatisplus.samples.crud.config;

import com.baomidou.mybatisplus.samples.crud.task.SinaRealTimeStockPriceTask;
import com.baomidou.mybatisplus.samples.crud.task.SohuStockPriceTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author joking
 */
@Configuration
@EnableScheduling
public class SpiderScheduleConfig {

    @Value("${spider.sohu-task.enable}")
    private boolean sohuEnable;

    @Value("${spider.sina-real-time-task.enable}")
    private boolean sinaEnable;

    private final SohuStockPriceTask sohuStockPriceTask;
    private final SinaRealTimeStockPriceTask sinaRealTimeStockPriceTask;

    public SpiderScheduleConfig(SohuStockPriceTask sohuStockPriceTask, SinaRealTimeStockPriceTask sinaRealTimeStockPriceTask) {
        this.sohuStockPriceTask = sohuStockPriceTask;
        this.sinaRealTimeStockPriceTask = sinaRealTimeStockPriceTask;
    }


    @Scheduled(cron = "${spider.sohu-task.cron}")
    public void spiderSohuStockData() {
        if(sohuEnable) {
            sohuStockPriceTask.start();
        }
    }

    @Scheduled(cron = "${spider.sina-real-time-task.cron}")
    public void spiderSinaRealTimePriceTask() {
        if(sinaEnable) {
            sinaRealTimeStockPriceTask.start();
        }
    }
}
