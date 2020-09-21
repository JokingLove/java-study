package com.joking.springbootguava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.joking.springbootguava.fastjson.User;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class GuavaCacheTest {

    static Map<String, User> dataBases = null;
    static {
        dataBases = new HashMap<>(100000);
        for (int i = 0; i < 100000; i++) {
            User user = new User(i, i + 10L, "张三" + i);
            dataBases.put(i + "", user);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicLong start = new AtomicLong(0);
        LoadingCache<String, User> userCache = CacheBuilder.newBuilder()
                .maximumSize(100)
                .expireAfterAccess(3, TimeUnit.SECONDS)
                .removalListener(notification -> {
//                    long l = System.currentTimeMillis();
//                    log.info("{} ms 移出缓存[{}]。。。", (l - start.get()), notification.getValue());
//                    log.info("{}", notification.getCause());
                })
                .build(new CacheLoader<String, User>() {

                    @Override
                    public User load(String s) {
//                        start.set(System.currentTimeMillis());
                        log.info("{} 缓存中没有找到，查询数据库。。。", s);
                        return dataBases.get(s.replace("user_", ""));
                    }
                });

//        dataBases.entrySet().stream()
//                .filter(e -> (Integer.parseInt(e.getKey()) < 100))
//                .forEach(e -> userCache.put("user_" + e.getKey(), e.getValue()));

        long s1 = System.currentTimeMillis();
        User user = userCache.getUnchecked("user_9");
        log.info("{} ms 第一次获取到： {}" ,(System.currentTimeMillis() - s1), user);

        long s2 = System.currentTimeMillis();
        user = userCache.getUnchecked("user_9");
        log.info("{} ms 第二次获取到： {}" ,(System.currentTimeMillis() - s2), user);

        TimeUnit.SECONDS.sleep(3);


        long s3 = System.currentTimeMillis();
        user = userCache.getUnchecked("user_9");
        log.info("{} ms 第三次获取到： {}" ,(System.currentTimeMillis() - s3), user);
    }
}
