package com.hbh.sms.model.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class MeterData extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long meterId;
    private String code;
    private Float value;
    private String createPerson;
    private Date createTime;
    private String updatePerson;
    private Date upLoadTime;
    private String unit;
    private Integer valveStatus;
    private Date startTime;
    private Date  smsDate;
}