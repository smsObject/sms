package com.hbh.sms.model.entity;

import java.io.Serializable;

/**
 * Created by hbh on 2016/7/6.
 */
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

    public String getComPort() {
        return comPort;
    }

    public void setComPort(String comPort) {
        this.comPort = comPort;
    }

    public Integer getBaudRate() {
        return baudRate;
    }

    public void setBaudRate(Integer baudRate) {
        this.baudRate = baudRate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSmsc() {
        return smsc;
    }

    public void setSmsc(String smsc) {
        this.smsc = smsc;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
