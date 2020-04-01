package com.fastrun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 股票实时分析表
 * </p>
 *
 * @author JOKING
 * @since 2019-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockAnalysis implements Serializable {

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
     * 股票code
     */
    private String stockCode;

    /**
     * 最近多少天的计算情况
     */
    private Integer lateDays;

    /**
     * 涨幅
     */
    private Double gain;

    /**
     * 增长率
     */
    private Double gainRate;

    /**
     * 计算时间
     */
    private LocalDateTime updateTime;

    /**
     * 价格日期
     */
    private LocalDate priceDate;

    /**
     * 上次价格，除数
     */
    private Double lastPrice;

    /**
     * 当前价格，被除数
     */
    private Double currentPrice;


}
