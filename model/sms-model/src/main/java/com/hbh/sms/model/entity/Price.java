/**
 * qccr.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.hbh.sms.model.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Price {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long year;

    private Byte timeLength;

    private Byte type;

    private String name;

    private String createPerson;

    private Date createTime;

    private String updatePerson;

    private Date updateTime;

    private List<PriceItem> priceItems;
}