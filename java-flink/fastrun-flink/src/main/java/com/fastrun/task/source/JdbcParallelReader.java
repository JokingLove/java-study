package com.fastrun.task.source;

import com.fastrun.config.MysqlConfig;
import com.fastrun.entity.StockPrice;
import com.fastrun.utils.DateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;

import java.sql.*;
import java.time.LocalDate;

/**
 * @author joking
 */
@Slf4j
public class JdbcParallelReader extends RichParallelSourceFunction<StockPrice> {

    private Connection connection;
    private PreparedStatement preparedStatement;

    private static final String SELECT_STOCK_PRICE_STATEMENT =
            "SELECT temp.name, tt.* FROM (SELECT t.* FROM tb_stock t ORDER BY id limit ?, 1) as temp " +
            "LEFT JOIN tb_stock_price_2019 tt on temp.code = tt.stock_code";

    @Override
    public void open(Configuration parameters) throws Exception {
        // 加载驱动
        Class.forName(MysqlConfig.DRIVER_NAME);
        // 获取连接
        connection = DriverManager.getConnection(MysqlConfig.URL, MysqlConfig.USER, MysqlConfig.PASSWORD);
        // statement
        preparedStatement = connection.prepareStatement(SELECT_STOCK_PRICE_STATEMENT);
        //
        super.open(parameters);
    }

    @Override
    public void run(SourceContext<StockPrice> ctx) throws Exception {
        try {
            // 设置参数
            preparedStatement.setInt(1, MysqlConfig.currentNum.getAndIncrement());
            // 执行查询
            ResultSet resultSet = preparedStatement.executeQuery();
            // 处理结果
            boolean hasNext = false;
            while(hasNext = resultSet.next()) {
                StockPrice stockPrice = handleResult(resultSet);
                ctx.collect(stockPrice);
            }
            resultSet.close();
            if(!hasNext) {
                log.info("数据处理完成！");
            }
        } catch (Exception e) {
            log.error("查询数据库出错-页码[{}], 错误信息：{}", MysqlConfig.currentNum.get(), e.getMessage(), e);
        }
    }

    private StockPrice handleResult(ResultSet rs) {
        try {
            StockPrice stockPrice = new StockPrice();
            stockPrice.setId(rs.getInt("id"));
            stockPrice.setStockId(rs.getInt("stock_id"));
            stockPrice.setStockCode(rs.getString("stock_code"));
            stockPrice.setStockName(rs.getString("name"));
            stockPrice.setPriceDate(LocalDate.parse(rs.getString("price_date")));
            stockPrice.setOpenPrice(rs.getBigDecimal("open_price"));
            stockPrice.setClosePrice(rs.getDouble("close_price"));
            stockPrice.setDiffPrice(rs.getBigDecimal("diff_price"));
            stockPrice.setDiffRange(rs.getBigDecimal("diff_range"));
            stockPrice.setDiffRangeStr(rs.getString("diff_range_str"));

            stockPrice.setLowstPrice(rs.getBigDecimal("lowst_price"));
            stockPrice.setHighstPrice(rs.getBigDecimal("highst_price"));
            stockPrice.setVolume(rs.getBigDecimal("volume"));
            stockPrice.setTurnover(rs.getBigDecimal("turnover"));
            stockPrice.setTurnoverRate(rs.getBigDecimal("turnover_rate"));
            stockPrice.setTurnoverRateStr(rs.getString("turnover_rate_str"));

            stockPrice.setUpdateTime(DateTimeUtil.parse(rs.getTimestamp("update_time")));

            return stockPrice;
        } catch (SQLException e) {
            log.error("ResultSet 中获取值错误：{}", e.getMessage(), e);
        }
        return null;
    }

    @Override
    public void cancel() {
        // 关闭连接
        try{
            super.close();
            if(connection != null) {
                connection.close();
            }
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (Exception e) {
            log.error("关闭数据库连接出错：{}", e.getMessage(), e);
        }
    }
}
