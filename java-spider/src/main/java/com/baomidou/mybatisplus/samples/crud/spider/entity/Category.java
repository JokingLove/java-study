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
@TableName("tb_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父类别名称
     */
    @TableField("parent")
    private String parent;

    /**
     * 类别编码
     */
    @TableField("code")
    private String code;

    /**
     * 分类名称
     */
    @TableField("name")
    private String name;

    /**
     * 新浪url
     */
    @TableField("sina_url")
    private String sinaUrl;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 最近抓取时间
     */
    @TableField("spider_time")
    private LocalDateTime spiderTime;


}
