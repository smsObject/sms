package com.hbh.sms.model.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hbh on 2017/8/13.
 */
@Data
public class UserPrice extends BaseDO {
    private Long userId;

    private Long meterId;

    private String userName;

    private String meterCode;

    private String address;

    private Integer userType;
    private Integer buyType;

    private String buyTypeName;

    private Float lastBuyWaterValue;

    private Date lastBuyWaterTime;

    private Float buyWaterValue;

    private BigDecimal realAmount;

    private BigDecimal payableAmount;

    private BigDecimal realableAmount;

    private BigDecimal getbackAmount;
}