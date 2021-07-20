package com.joking.yaml.futrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = new CompletableFuture<>();
        for (int i = 1; i < 4; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(finalI);
                    future.completeExceptionally(new Exception("异常了！"));
//                    future.complete("哈哈" + finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        future.exceptionally((e) -> {
            System.out.println(e.getMessage());
            return null;
        });
//        String s = future.get();
        future.thenAccept(System.out::println);
        future.thenRunAsync(() -> {
            System.out.println("async  end");
        }).join();
//        System.out.println(s);
        System.out.println("end");

    }
}
