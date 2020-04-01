package com.fastrun.task.batch;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.operators.Order;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * @author joking
 */
public class WordCountExample {
    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        DataSet<String> source = env.fromElements(
                "hello world test add notifice aa bb cc dd dd aa hello world  hello",
                "what are you doing hello world");

        source.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String text, Collector<Tuple2<String, Integer>> out) throws Exception {
                if (StringUtils.isNotEmpty(text)) {
                    String[] split = text.split("\\W+");
                    Arrays.asList(split).forEach(i -> out.collect(new Tuple2<String, Integer>(i, 1)));
                }
            }
        })
                .groupBy(0)
                .sum(1)
                .sortPartition(1, Order.ASCENDING)
                .print();


    }
}
