package com.fastrun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 股票分类表
 * </p>
 *
 * @author JOKING
 * @since 2019-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 父类别名称
     */
    private String parent;

    /**
     * 类别编码
     */
    private String code;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 新浪url
     */
    private String sinaUrl;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 最近抓取时间
     */
    private LocalDateTime spiderTime;


}
