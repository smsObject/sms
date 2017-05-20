package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hbh
 */

@Data
public class UseTypeDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String remarks;
    private Float waterPrice;
    private Float basicPrice;
    private Integer isUseStep;
}