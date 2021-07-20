package com.joking.yaml.futrue;

import java.util.concurrent.CompletableFuture;
import org.omg.PortableInterceptor.ObjectReferenceFactory;

/**
 * @author guoyongqiang
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future3 = CompletableFuture
            .supplyAsync(() -> sleepAndReturn(3000, "3000"));
        CompletableFuture<String> future2 = CompletableFuture
            .supplyAsync(() -> sleepAndReturn(2000, "2000"));
        CompletableFuture<String> future1 = CompletableFuture
            .supplyAsync(() -> sleepAndReturn(1000, "1000"));

        CompletableFuture<Object> future = CompletableFuture.anyOf(future1, future2, future3);
//        CompletableFuture<Void> allFuture = CompletableFuture.allOf(future1, future2, future3);

//        future.thenAccept(System.out::println);
        System.out.println(future.get());
//        System.out.println(future1.get() + ":" + future2.get() + ":" + future3.get());
    }

    private static String sleepAndReturn(int i, String s) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
