package com.joking.springboot.constants;

public enum UsdtStateEnum {

    /**
     * usdt 状态
     */
    APPLY((byte)0, "申请中"),
    PASS((byte)1, "已审批"),
    REJECT((byte)2, "已取消")
    ;

    private final byte code;
    private final String desc;


    UsdtStateEnum(byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public byte getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
