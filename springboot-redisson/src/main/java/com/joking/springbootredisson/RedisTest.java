package com.joking.springbootredisson;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;

public class RedisTest {

    public static void main(String[] args) {
        Config config = new Config();
        config.setTransportMode(TransportMode.EPOLL);
//        config.useSingleServer()
//                .setAddress("redis://127.0.0.1:6379");
//                .addNodeAddress("redis://127.0.0.1:6379");
        config.useClusterServers()
                .addNodeAddress("redis://127.0.0.1:6379");



        RedissonClient redis = Redisson.create(config);
        RAtomicLong myLong = redis.getAtomicLong("myLong");
        myLong.compareAndSet(3, 401);

        RKeys keys = redis.getKeys();
        keys.count();

    }
}
