package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hbh on 2017/7/15.
 */

@Data
public class PriceInterval implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Float startValue;

    private Float endValue;

    private Long priceId;

    private String createPerson;

    private Date createTime;

    private String updatePerson;

    private Date updateTime;
}