package com.fastrun.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 股票分类关系表
 * </p>
 *
 * @author JOKING
 * @since 2019-10-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class StockCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 股票ID
     */
    private Integer stockId;

    /**
     * 分类ID
     */
    private Integer categoryId;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
