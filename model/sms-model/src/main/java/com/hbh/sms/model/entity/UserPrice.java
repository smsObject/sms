package com.hbh.sms.model.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hbh on 2017/8/13.
 */
@Data
public class UserPrice extends BaseDO {
    //用户id
    private Long userId;
    //仪表id
    private Long meterId;
    //用户名称
    private String userName;
    //仪表code
    private String meterCode;
    //安装地址
    private String address;
    //使用类型
    private Integer userType;
    //购买类型
    private Long buyType;

    private String buyTypeName;
    //最后一次购买的水量
    private Float lastBuyWaterValue;
    //最后一次购买水时间
    private Date lastBuyWaterTime;
    //购买水量
    private Float buyWaterValue;
    //实际支付金额
    private BigDecimal realAmount;
    //应付金额
    private BigDecimal payableAmount;
    //实收金额
    private BigDecimal realableAmount;
    //找回金额
    private BigDecimal getbackAmount;
    //价格模版信息
    private String priceTemplateInfo;
}