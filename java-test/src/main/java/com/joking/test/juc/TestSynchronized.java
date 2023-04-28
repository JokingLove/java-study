package com.joking.test.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author joking
 */
public class TestSynchronized {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Singleton instance = Singleton.getInstance();
                System.out.println(instance);
            });
            thread.start();
            countDownLatch.countDown();
        }


    }



}
