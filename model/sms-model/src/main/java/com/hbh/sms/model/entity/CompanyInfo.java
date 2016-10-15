package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CompanyInfo implements Serializable {
    private Integer id;

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

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatedate;

    private static final long serialVersionUID = 1L;
}