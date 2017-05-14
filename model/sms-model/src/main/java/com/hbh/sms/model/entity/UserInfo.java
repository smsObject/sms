package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo extends BaseDO implements Serializable {
    private String name;
    private Integer userType;
    private Integer buyType;
    private String address;
    private String idNo;
    private String phone;
    private String mobilPhone;
    private String fixTime;
    private String openingTime;
    private Float basicAllowance;
    private Float remainingBalance;
    private Integer status;
    private Integer valveStatus;
    private Integer payType;
    private Integer householdPerson;
    private String remarks;
    private static final long serialVersionUID = 1L;
}