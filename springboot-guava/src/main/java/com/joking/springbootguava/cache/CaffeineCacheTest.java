package com.joking.springbootguava.cache;

import com.github.benmanes.caffeine.cache.*;
import com.joking.springbootguava.fastjson.User;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class CaffeineCacheTest {
    static Map<String, User> dataBases = null;

    static {
        dataBases = new HashMap<>(100000);
        for (int i = 0; i < 100000; i++) {
            User user = new User(i, i + 10L, "张三" + i);
            dataBases.put(i + "", user);
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        AsyncLoadingCache<String, User> userCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .buildAsync(new AsyncCacheLoader<String, User>() {
                    @Override
                    public @NonNull CompletableFuture<User> asyncLoad(@NonNull String key, @NonNull Executor executor) {
                        log.info("{} 缓存中没有找到，查询数据库。。。", key);
                        return CompletableFuture.completedFuture(dataBases.get(key.replace("user_", "")));
                    }
                });

        long s1 = System.currentTimeMillis();
//        User user = userCache.get("user_9", k -> {
//            log.info("{} 缓存中没有找到，查询数据库。。。", k);
//            return dataBases.get(k.replace("user_", ""));
//        });
        CompletableFuture<User> user = userCache.get("user_9");
        log.info("{} ms 第一次获取到： {}", (System.currentTimeMillis() - s1), user.get());

        long s2 = System.currentTimeMillis();
        user = userCache.get("user_9");
        log.info("{} ms 第二次获取到： {}", (System.currentTimeMillis() - s2), user.get());

        TimeUnit.SECONDS.sleep(3);


        long s3 = System.currentTimeMillis();
        user = userCache.get("user_9");
        log.info("{} ms 第三次获取到： {}", (System.currentTimeMillis() - s3), user.get());
    }

    public static void main1(String[] args) throws InterruptedException {
        AtomicLong start = new AtomicLong(0);
        LoadingCache<String, User> userCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .build(new CacheLoader<String, User>() {
                    @Override
                    public @Nullable User load(@NonNull String s) throws Exception {
                        log.info("{} 缓存中没有找到，查询数据库。。。", s);
                        return dataBases.get(s.replace("user_", ""));
                    }
                });

//        dataBases.entrySet().stream()
//                .filter(e -> (Integer.parseInt(e.getKey()) < 100))
//                .forEach(e -> userCache.put("user_" + e.getKey(), e.getValue()));

        long s1 = System.currentTimeMillis();
//        User user = userCache.get("user_9", k -> {
//            log.info("{} 缓存中没有找到，查询数据库。。。", k);
//            return dataBases.get(k.replace("user_", ""));
//        });
        User user = userCache.get("user_9");
        log.info("{} ms 第一次获取到： {}", (System.currentTimeMillis() - s1), user);

        long s2 = System.currentTimeMillis();
        user = userCache.get("user_9");
        log.info("{} ms 第二次获取到： {}", (System.currentTimeMillis() - s2), user);

        TimeUnit.SECONDS.sleep(3);


        long s3 = System.currentTimeMillis();
        user = userCache.get("user_9");
        log.info("{} ms 第三次获取到： {}", (System.currentTimeMillis() - s3), user);
    }
}
