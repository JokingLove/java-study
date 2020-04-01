package com.fastrun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 股票实体表
 * </p>
 *
 * @author JOKING
 * @since 2019-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 类别ID
     */
    private Integer categoryId;

    /**
     * 符号
     */
    private String symbol;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 新浪url
     */
    private String sinaUrl;

    /**
     * 抓取时间
     */
    private LocalDateTime spiderTime;

    /**
     * 计算时间
     */
    private LocalDateTime computeTime;


}
