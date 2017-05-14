package com.hbh.sms.model.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hbh
 */

@Data
public class Meter extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String meterNo;
    private String meterCode;
    private Long controllerId;
    private String meterName;
    private Date fixDate;
    private Integer useType;
    private String userType;
    private Float basicValue;
    private Integer buyWay;
    private Integer meterModel;
    private String unit;
    private Float baseValue;
    private String controllerName;
    private Date startTime;
    private String mc1;
    private String mc2;
    private String mc3;
    private Date lastUpLoadTime ;
    private Integer lastValveStatus;
    private Float lastValue;
    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private String timing1;
    private String timing2;
    private String timing3;
    private String timing4;
    private Date startUpdateTime;
    private Integer activateTime;
}