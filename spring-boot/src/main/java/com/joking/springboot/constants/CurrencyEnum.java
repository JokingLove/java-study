package com.joking.springboot.constants;

public enum CurrencyEnum {

    /**
     *  币种
     */
    CNY("cny", "人民币"),
    COP("COP", "哥伦比亚"),
    INR("INR","印度"),
    BRL("BRL", "巴西雷尔"),
    MXN("MXN", "墨西哥"),
    PHP("PHP", "菲律宾"),
    THB("THB", "泰国")
    ;

    private final String code;
    private final String desc;


    CurrencyEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
