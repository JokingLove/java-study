package com.joking.yaml;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * 配置文件管理工具库
 * @author guoyongqiang
 */
public class TypeSafeConfigTest {

    public static void main(String[] args) {
        System.setProperty("hello", "ddd");
        Config config = ConfigFactory.load();
        System.out.println(config);

        Config testConfig = ConfigFactory.load("test.conf");
        System.out.println(testConfig);
    }
}
