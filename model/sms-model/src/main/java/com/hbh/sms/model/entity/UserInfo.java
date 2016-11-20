package com.hbh.sms.model.entity;

import java.io.Serializable;

public class UserInfo extends BaseDO implements Serializable {
    private String userName;

    private String idNo;

    private String phone;

    private Integer householdPerson;

    private Integer payType;

    private String paymentBank;

    private String bankAccount;

    private String remarks;

    private static final long serialVersionUID = 1L;
}