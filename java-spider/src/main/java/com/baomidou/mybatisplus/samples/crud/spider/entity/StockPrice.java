package com.baomidou.mybatisplus.samples.crud.spider.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 股票价格记录表
 * </p>
 *
 * @author JOKING
 * @since 2019-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_stock_price")
public class StockPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 股票ID
     */
    @TableField("stock_id")
    private Integer stockId;

    /**
     * 股票代码
     */
    @TableField("stock_code")
    private String stockCode;

    /**
     * 日期
     */
    @TableField("price_date")
    private LocalDate priceDate;

    /**
     * 开盘价
     */
    @TableField("open_price")
    private Float openPrice;

    /**
     * 收盘价
     */
    @TableField("close_price")
    private Float closePrice;

    /**
     * 涨跌额
     */
    @TableField("diff_price")
    private Float diffPrice;

    /**
     * 涨跌幅
     */
    @TableField("diff_range")
    private Float diffRange;

    /**
     * 涨跌幅字符串
     */
    @TableField("diff_range_str")
    private String diffRangeStr;

    /**
     * 最低价
     */
    @TableField("lowst_price")
    private Float lowstPrice;

    /**
     * 最高价
     */
    @TableField("highst_price")
    private Float highstPrice;

    /**
     * 成交量
     */
    @TableField("volume")
    private BigDecimal volume;

    /**
     * 成交额
     */
    @TableField("turnover")
    private BigDecimal turnover;

    /**
     * 换手率
     */
    @TableField("turnover_rate")
    private Float turnoverRate;

    /**
     * 换手率字符串
     */
    @TableField("turnover_rate_str")
    private String turnoverRateStr;

    @TableField("update_time")
    private LocalDate updateTime;

}
