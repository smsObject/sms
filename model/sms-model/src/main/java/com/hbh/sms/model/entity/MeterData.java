package com.hbh.sms.model.entity;

import java.io.Serializable;
import java.util.Date;

public class MeterData extends  BaseDo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer meterId;

    private String code;

    private Float value;

    private Date uploadTime;

    private String unit;

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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public Integer getMeterId() {
        return meterId;
    }

    public void setMeterId(Integer meterId) {
        this.meterId = meterId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}