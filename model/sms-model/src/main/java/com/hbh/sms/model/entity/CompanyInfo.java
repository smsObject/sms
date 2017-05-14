package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompanyInfo extends BaseDO implements Serializable {
    private String companyName;
    private String companyCode;
    private String serviceCall;
    private String serviceFax;
    private String serviceEmail;
    private String complaintCall;
    private String serviceWebsite;
    private String address;
    private String zipCode;
    private String contactPerson;
    private String taxNo;
    private String bankName;
    private String bankAccount;
    private String allocatedRegion;
    private static final long serialVersionUID = 1L;
}