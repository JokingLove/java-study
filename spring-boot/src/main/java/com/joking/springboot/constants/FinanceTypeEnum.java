package com.joking.springboot.constants;


/**
 * 资金记录类型
 * @author joking
 */
public enum FinanceTypeEnum {
    // 资金记录类型
    ORDER(1, "下单"),
    TRANSFER(2, "转账"),
    USDT(3, "回U"),
    ADDBALANCE(4, "上分"),
    SUBBALANCE(5, "下分")
    ;

    /**
     * 类型： 1 下单，2 转账 3 回U，4 上分 5 下分
     */

    private final int code;
    private final String desc;

    FinanceTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
