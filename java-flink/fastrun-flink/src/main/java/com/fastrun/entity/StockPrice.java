package com.fastrun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 股票价格记录表
 * </p>
 *
 * @author JOKING
 * @since 2019-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockPrice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 股票ID
     */
    private Integer stockId;

    /**
     * name
     */
    private String stockName;

    /**
     * 股票代码
     */
    private String stockCode;

    /**
     * 日期
     */
    private LocalDate priceDate;

    /**
     * 开盘价
     */
    private BigDecimal openPrice;

    /**
     * 收盘价
     */
    private Double closePrice;

    /**
     * 涨跌额
     */
    private BigDecimal diffPrice;

    /**
     * 涨跌幅
     */
    private BigDecimal diffRange;

    /**
     * 涨跌幅字符串
     */
    private String diffRangeStr;

    /**
     * 最低价
     */
    private BigDecimal lowstPrice;

    /**
     * 最高价
     */
    private BigDecimal highstPrice;

    /**
     * 成交量
     */
    private BigDecimal volume;

    /**
     * 成交额
     */
    private BigDecimal turnover;

    /**
     * 换手率
     */
    private BigDecimal turnoverRate;

    /**
     * 换手率字符串
     */
    private String turnoverRateStr;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
