package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MeterData extends BaseDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer meterId;

    private String code;

    private Float value;

    private Date uploadTime;

    private String unit;

}