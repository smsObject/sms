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

    public Concentrator(String comPort , Integer baudRate , String manufacturer , String model){
        this.comPort=comPort;
        this.baudRate=baudRate;
        this.manufacturer=manufacturer;
        this.model=model;
    }

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
}
