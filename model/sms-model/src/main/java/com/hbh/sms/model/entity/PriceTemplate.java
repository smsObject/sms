/**
 * qccr.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class PriceTemplate implements Serializable{
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String remarks;

    private String createPerson;

    private Date createTime;

    private String updatePerson;

    private Date updateTime;

    private Long parentId;

    private List<PriceTemplate> childs;
}