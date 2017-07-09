/**
 * qccr.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.hbh.sms.model.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

@Data
public class PriceItem implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Float startValue;

    private Float endValue;

    private Long priceId;

    private Long templateId;

    private Float price;

    private Map<Long,Float> prices;

    private String createPerson;

    private Date createTime;

    private String updatePerson;

    private Date updateTime;
}