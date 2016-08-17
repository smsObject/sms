package com.hbh.sms.model.entity;

import java.io.Serializable;
import java.util.Date;

public class MeterData extends  BaseDo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer meterid;

    private String code;

    private Float value;

    private Date uploadtime;

    private String unit;

    public Integer getMeterid() {
        return meterid;
    }

    public void setMeterid(Integer meterid) {
        this.meterid = meterid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Date getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(Date uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }
}