package com.joking.springbootstart.runtime;

public class RuntimeTest {

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("aha ,wo shutdown   le    a....");
            }
        });
    }


    public static void main(String[] args) throws InterruptedException {
        System.out.println("正在运行中。。。");
        Thread.sleep(1000000);
    }
}
