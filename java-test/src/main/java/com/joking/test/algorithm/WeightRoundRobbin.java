package com.joking.test.algorithm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * 平滑加权轮训算法
 */
public class WeightRoundRobbin {

    static final List<ServerInfo> serverList = new ArrayList<>();

    static  {
        serverList.add(new ServerInfo("A", 6, 0));
        serverList.add(new ServerInfo("B", 3, 0));
        serverList.add(new ServerInfo("C", 1, 0));
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String server = getServer();
            System.out.printf("%2s", server);
        }
    }


    public static String getServer() {
//        printServerInfo();
        // 1.计算静态权重之和 sum
        int totalWeight = 0;
        for (ServerInfo serverInfo : serverList) {
            totalWeight += serverInfo.getWeight();
        }
//        System.out.println(totalWeight);
        // 2.动态权重 += 静态权重，找到最大的
        ServerInfo maxServer = null;
        for (ServerInfo serverInfo : serverList) {
            serverInfo.setCurWeight(serverInfo.getWeight() + serverInfo.getCurWeight());
            // 找到权重最大的server
            if (maxServer == null || serverInfo.getCurWeight() > maxServer.getCurWeight()) {
                maxServer = serverInfo;
            }
        }
        // 3.将最大静态权重的静态权重重新赋值 -= sum
        assert maxServer != null;
        maxServer.setCurWeight(maxServer.getCurWeight() - totalWeight);

        return maxServer.getIp();
    }

    private static void printServerInfo() {
        serverList.forEach(System.out::println);
    }


    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ServerInfo {
        /**
         * 服务器主机名
         */
        private String ip;

        /**
         * 静态权重
         */
        private Integer weight;

        /**
         * 当前动态权重
         */
        private Integer curWeight;
    }
}
