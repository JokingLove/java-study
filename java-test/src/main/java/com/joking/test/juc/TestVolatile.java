package com.joking.test.juc;

import java.util.concurrent.TimeUnit;

public class TestVolatile {

    private volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        TestVolatile testVolatile = new TestVolatile();
        new Thread(() -> {
            System.out.println("t1 started!");
            while(testVolatile.flag) {
                System.out.println("t1  end");
            }
        }, "t1").start();

        TimeUnit.SECONDS.sleep(3);

        testVolatile.flag = true;

        while(true) {}
    }

}
