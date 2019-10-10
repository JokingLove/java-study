package com.baomidou.mybatisplus.samples.crud.spider.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 股票实时分析表
 * </p>
 *
 * @author JOKING
 * @since 2019-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_stock_analysis")
public class StockAnalysis implements Serializable {

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
     * 股票code
     */
    @TableField("stock_code")
    private String stockCode;

    /**
     * 最近多少天的计算情况
     */
    @TableField("late_days")
    private Integer lateDays;

    /**
     * 涨幅
     */
    @TableField("gain")
    private Double gain;

    /**
     * 增长率
     */
    @TableField("gain_rate")
    private Double gainRate;

    /**
     * 计算时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;


}
