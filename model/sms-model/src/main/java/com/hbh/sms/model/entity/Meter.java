package com.hbh.sms.model.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hbh on 2016/7/19.
 */

@Data
public class Meter extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    private String meterNo;
    /**
     * 编码
     */
    private String meterCode;
    /**
     * 所属集中器
     */
    private Long controllerId;
    /**
     * 仪表名称
     */
    private String meterName;
    /**
     * 安装时间
     */
    private Date fixDate;
    /**
     * 使用类型
     */
    private Integer useType;
    /**
     * 用户类型
     */
    private String userType;
    /**
     * 基础值
     */
    private Float basicValue;
    /**
     * 付费方式
     */
    private Integer buyWay;
    /**
     * 仪表型号
     */
    private Integer meterModel;
    /**
     * 水表单位
     */
    private String unit;

    /**
     *初始值
     */
    private Float baseValue;

    /**
     * 所属GSM名称
     */
    private String controllerName;

    private Date startTime;

    private String mc1;

    private String mc2;

    private String mc3;

    private Date lastUpLoadTime ;

    private Integer lastValveStatus;

    private Float lastValue;

    private String day1;
    private String day2;
    private String day3;
    private String day4;

    private String timing1;
    private String timing2;
    private String timing3;
    private String timing4;

    private Date startUpdateTime;

    private Integer activateTime;
}