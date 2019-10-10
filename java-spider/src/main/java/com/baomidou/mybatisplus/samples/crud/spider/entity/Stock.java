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
 * 
 * </p>
 *
 * @author JOKING
 * @since 2019-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类别ID
     */
    @TableField("category_id")
    private Integer categoryId;

    /**
     * 符号
     */
    @TableField("symbol")
    private String symbol;

    /**
     * 编码
     */
    @TableField("code")
    private String code;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 新浪url
     */
    @TableField("sina_url")
    private String sinaUrl;

    /**
     * 抓取时间
     */
    @TableField("spider_time")
    private LocalDateTime spiderTime;

    /**
     * 计算时间
     */
    @TableField("compute_time")
    private LocalDateTime computeTime;


}
