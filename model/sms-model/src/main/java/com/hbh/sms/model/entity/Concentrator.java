package com.hbh.sms.model.entity;

import lombok.Data;

/**
 * Created by hbh
 */
@Data
public class Concentrator  extends BaseDO {
    private String  comPort;
    private Integer baudRate;
    private String  manufacturer;
    private String  model;
    private String  code;
    private String  smsc;
    private Integer isOnline;
    private  String name;
}