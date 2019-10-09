package com.baomidou.mybatisplus.samples.crud.model;

import lombok.Data;
import lombok.ToString;

/**
 * @author joking
 */
@Data
@ToString
public class SohuResult {

    private int status;
    private String[][] hq;
    private String code;
    private Object[] stat;
}
