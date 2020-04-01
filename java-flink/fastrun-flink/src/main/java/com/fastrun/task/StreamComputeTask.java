package com.fastrun.task;

import com.esotericsoftware.minlog.Log;
import com.fastrun.config.MysqlConfig;
import com.fastrun.entity.StockPrice;
import com.fastrun.task.source.JdbcParallelReader;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.stream.Collectors;

/**
 * @author joking
 */
public class StreamComputeTask {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<StockPrice> stockPriceDataStreamSource = env.addSource(new JdbcParallelReader());
        try {
//            stockPriceDataStreamSource
//                    .flatMap((StockPrice stockPrice, Collector<StockPrice> out) -> {
//                        out.collect(stockPrice);
//                    }).co

            env.execute("stream compute task");
            System.out.println("处理完成：" + MysqlConfig.currentNum.get());
        } catch (Exception e) {
            Log.error("出错了。");
        }
    }
}
