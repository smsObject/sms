package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MeterData extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 仪表id
     */
    private Long meterId;
    /**
     * 仪表编码
     */
    private String code;
    /**
     * 数据值
     */
    private Float value;
    /**
     *创建人
     */
    private String createPerson;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updatePerson;
    /**
     * 更新时间
     */
    private Date upLoadTime;
    /**
     * 单位
     */
    private String unit;

    /**
     *阀状态 0 阀开 1是阀关
     */
    private Integer valveStatus;

    private Date startTime;

    private Date  smsDate;
}