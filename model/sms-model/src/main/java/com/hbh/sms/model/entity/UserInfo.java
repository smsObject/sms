package com.hbh.sms.model.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class UserInfo extends BaseDO implements Serializable {
    /**
     * 用户姓名
     */
    private String name;

    /**
     * 仪表id
     */
    private Long meterId;
    /**
     * 用户类型
     */
    private Integer userType;
    /**
     * 购水方式
     */
    private Integer buyType;
    /**
     * 使用类型
     */
    private Integer useType;
    /**
     * 用户地址
     */
    private String address;
    /**
     * 身份证号
     */
    private String idNo;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 联系手机
     */
    private String mobilePhone;
    /**
     * 安装时间
     */
    private String fixTime;
    /**
     * 开户时间
     */
    private String openingTime;
    /**
     * 基本余量 预付费用户
     */
    private Float basicAllowance;
    /**
     * 结余金额 预付费用户
     */
    private Float remainingBalance;
    /**
     * 使用状态
     */
    private Integer status;
    /**
     * 阀状态
     */
    private Integer valveStatus;
    /**
     * 支付类型
     */
    private String payType;

    /**
     * 人户
     */
    private Integer householdPerson;

    /**
     * 累计使用度数
     */
    private Float totalBuyValue;

    /**
     * 当前度数
     */
    private Float currentUseValue;

    /**
     * 备注
     */
    private String remarks;
    private String useTypeName;
    private String buyTypeName;
    private static final long serialVersionUID = 1L;

//    费用预估记录表
//            用户id, 仪表id,用水类型,上次水量,本次水量,使用水量,费用,费用模版信息

}