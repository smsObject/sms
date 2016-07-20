package com.hbh.sms.model.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hbh on 2016/7/19.
 */
public class Meter extends BaseDo {

    /**编号*/
    private String meterNo;
    /**编码*/
    private String meterCode;
    /**所属集中器*/
    private Integer concentratorId;
    /**仪表名称*/
    private String meterName;
    /**安装时间*/
    private Date fixDate;
    /**使用类型*/
    private Integer useType;
    /**用户类型*/
    private String userType;
    /**基础值*/
    private Float basicValue;
    /**付费方式*/
    private Integer buyWay;
    /**仪表型号*/
    private Integer meterModel;

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public String getMeterCode() {
        return meterCode;
    }

    public void setMeterCode(String meterCode) {
        this.meterCode = meterCode;
    }

    public Integer getConcentratorId() {
        return concentratorId;
    }

    public void setConcentratorId(Integer concentratorId) {
        this.concentratorId = concentratorId;
    }

    public String getMeterName() {
        return meterName;
    }

    public void setMeterName(String meterName) {
        this.meterName = meterName;
    }

    public Date getFixDate() {
        return fixDate;
    }

    public void setFixDate(Date fixDate) {
        this.fixDate = fixDate;
    }

    public Integer getUseType() {
        return useType;
    }

    public void setUseType(Integer useType) {
        this.useType = useType;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Float getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(Float basicValue) {
        this.basicValue = basicValue;
    }

    public Integer getBuyWay() {
        return buyWay;
    }

    public void setBuyWay(Integer buyWay) {
        this.buyWay = buyWay;
    }

    public Integer getMeterModel() {
        return meterModel;
    }

    public void setMeterModel(Integer meterModel) {
        this.meterModel = meterModel;
    }
}
