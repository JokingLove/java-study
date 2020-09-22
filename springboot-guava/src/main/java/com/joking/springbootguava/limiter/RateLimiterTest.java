package com.joking.springbootguava.limiter;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class RateLimiterTest {

    public static void main(String[] args) throws Exception {
        RateLimiter limiter = RateLimiter.create(3);

        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        AtomicInteger num = new AtomicInteger();

//        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                if(!limiter.tryAcquire()) {
                    log.info("线程[{}]访问被限制。。。", Thread.currentThread().getName());
                } else {
                    num.incrementAndGet();
                    log.info("线程[{}]获取到锁。。。", Thread.currentThread().getName());
                }
            });

//            scheduledExecutorService.schedule(thread, 200, TimeUnit.MILLISECONDS);
//            scheduledExecutorService.scheduleWithFixedDelay(thread, 0,200L, TimeUnit.MILLISECONDS);
            scheduledExecutorService.scheduleAtFixedRate(thread, 0,200L, TimeUnit.MILLISECONDS);
//        }
        log.info("总共获取到锁[{}]个。。。", num.get());
//        for (int i = 0; i < 10; i++) {
//            if(!limiter.tryAcquire()) {
//                log.info("{} 线程[{}]访问被限制。。。", i, Thread.currentThread().getName());
//            } else {
//                log.info("{} 线程[{}]获取到锁。。。", i, Thread.currentThread().getName());
//            }
//        }

    }
}
