package com.joking.springboot.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BalanceStateEnum {
    /**
     * 结算状态 0 未结算 1 结算中  2 已结算
     */
    NO((byte)0, "未结算"),
    DOING((byte)1, "结算中"),
    FINISHED((byte)2, "已结算")
    ;



    private final byte code;
    private final String desc;


}
