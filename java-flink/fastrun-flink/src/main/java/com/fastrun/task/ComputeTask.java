package com.fastrun.task;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.io.jdbc.JDBCInputFormat;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.types.Row;
import org.apache.flink.util.Collector;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author joking
 */
@Slf4j
public class ComputeTask {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://local.com:3307/crown";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String SELECT_STOCK_STATEMENT = "SELECT * from tb_stock";
    private static final String SELECT_STOCK_PRICE_STATEMENT = "SELECT * FROM tb_stock_price_2019 where stock_code=?";
    static List<String>  list = Arrays.asList("300730", "600012", "600385");

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        List<String> stockCodeList = getStockCodeList(env);

        RowTypeInfo stockPriceRowTypeInfo = new RowTypeInfo(
                TypeInformation.of(Integer.class),
                TypeInformation.of(Integer.class),
                TypeInformation.of(String.class),
                TypeInformation.of(Date.class),
                TypeInformation.of(BigDecimal.class),
                TypeInformation.of(BigDecimal.class),
                TypeInformation.of(BigDecimal.class),
                TypeInformation.of(BigDecimal.class),
                TypeInformation.of(String.class),
                TypeInformation.of(BigDecimal.class),
                TypeInformation.of(BigDecimal.class),
                TypeInformation.of(BigDecimal.class),
                TypeInformation.of(BigDecimal.class),
                TypeInformation.of(BigDecimal.class),
                TypeInformation.of(String.class),
                TypeInformation.of(Date.class)
        );

        JDBCInputFormat.JDBCInputFormatBuilder jdbcInputFormatBuilder = JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername(DRIVER_NAME)
                .setDBUrl(URL)
                .setUsername(USER)
                .setPassword(PASSWORD)
                .setQuery(SELECT_STOCK_PRICE_STATEMENT)
                .setRowTypeInfo(stockPriceRowTypeInfo);

        for (String item : stockCodeList) {
            jdbcInputFormatBuilder.setParametersProvider(() -> {
                return new String[][]{{item}};
            });
            JDBCInputFormat jdbcInputFormat = jdbcInputFormatBuilder.finish();
            DataSource<Row> input = env.createInput(jdbcInputFormat);
            long count = input.count();
            System.out.println("计算结果：" + item + "==" + count);
        }


    }

    private static List<String> getStockCodeList(ExecutionEnvironment env) {
        List<String> stockCodeList = new ArrayList<>();
        RowTypeInfo stockRowTypeInfo = new RowTypeInfo(
                TypeInformation.of(Integer.class),
                TypeInformation.of(Integer.class),
                TypeInformation.of(String.class),
                TypeInformation.of(String.class),
                TypeInformation.of(String.class),
                TypeInformation.of(Date.class),
                TypeInformation.of(String.class),
                TypeInformation.of(Date.class),
                TypeInformation.of(Date.class)
        );

        JDBCInputFormat jdbcInputFormat = JDBCInputFormat.buildJDBCInputFormat()
                .setDrivername(DRIVER_NAME)
                .setDBUrl(URL)
                .setUsername(USER)
                .setPassword(PASSWORD)
                .setQuery(SELECT_STOCK_STATEMENT)
                .setRowTypeInfo(stockRowTypeInfo)
                .finish();
        DataSource<Row> input = env.createInput(jdbcInputFormat);
        try {
            List<String> collect = input.flatMap((Row row, Collector<String> out) -> {
                out.collect(String.valueOf(row.getField(3)));
            }).returns(String.class).collect();
            return collect;
        } catch (Exception e) {
            log.error("出错了： {}", e.getMessage(), e);
        }
        return null;
    }
}
