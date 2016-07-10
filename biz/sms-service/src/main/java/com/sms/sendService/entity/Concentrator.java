package com.sms.sendService.entity;

import java.io.Serializable;

/**
 * Created by hbh on 2016/7/6.
 */
public class Concentrator implements Serializable {
    private Integer id;
    private String  comPort;
    private Integer baudRate;
    private String  manufacturer;
    private String  model;
    private String  code;
    private String  smsc;

    public  Concentrator (String comPort , Integer baudRate ,String manufacturer , String model){
        this.comPort=comPort;
        this.baudRate=baudRate;
        this.manufacturer=manufacturer;
        this.model=model;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
