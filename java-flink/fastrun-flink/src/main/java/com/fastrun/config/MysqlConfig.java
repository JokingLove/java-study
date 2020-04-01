package com.fastrun.config;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * mysql 全局配置
 * @author joking
 */
public class MysqlConfig {
    // 当前 num
    public static AtomicInteger currentNum = new AtomicInteger(1);
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://local.com:3307/crown";
    public static final String USER = "root";
    public static final String PASSWORD = "root";

}
