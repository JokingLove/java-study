package com.joking.entity.dto;

import lombok.Data;

/**
 * @author zhangyj
 * @title: OrderResultDTO
 * @projectName lepu-market
 * @description: TODO
 * @date 2023/3/615:26
 */
@Data
public class OrderResultDTO {
    //订单编号
    private String orderCode;
    //门店名称
    private String storeName;
    private String storeCode;
    //合作方式 1 代销  2现款
    private Integer coopType;
    //订单金额(元)
    private String amount;
    //订单状态 1 新建  2 完成  3取消
    private Integer status;
    //套餐名称
    private String setMealName;
    //活动政策类型
    private String activityType;
    //负责业务员
    private String businessName;
    //负责业务员
    private String businessCode;
    //审核状态 1 待审核 2审核通过 3拒绝
    private Integer auditStatus;
    //下单时间
    private String createTime;
    //发货状态 1待发货 2已发货  3已收货 4已上传收货凭证
    private Integer shipStatus;
    //售后状态 1 已新建 2 售后中 3已完成 4已取消
    private Integer aftersaleStatus;

    //门店状态
    private String storeStatus;
    //合作方式
    private String storeCoopType;
    //是否预警 1是0否
    private int isWarn;
    //所在大区
    private String region;
    //所在省区
    private String province;


}