package com.joking.javamockito;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class ScheduleConfig {

    private static int i = 0;

    // 2 秒
//    @Scheduled(fixedDelay = 2 * 1000)
    @Scheduled(fixedDelayString = "#{${test.delay} * 1000}")
    public void testSchedule() {
        System.out.println("开始执行。。。");
        System.out.println(i++);
    }
}
