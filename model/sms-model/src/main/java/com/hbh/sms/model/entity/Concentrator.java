package com.hbh.sms.model.entity;

import lombok.Data;

/**
 * Created by hbh on 2016/7/6.
 */
@Data
public class Concentrator  extends  BaseDo{
    /**com组件名称*/
    private String  comPort;
    /**波特率*/
    private Integer baudRate;
    /**生产商*/
    private String  manufacturer;
    /**型号*/
    private String  model;
    /**手机号*/
    private String  code;
    /**短信中心号*/
    private String  smsc;

    private Integer isOnline;

    private  String name;
}