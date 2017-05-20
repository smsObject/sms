package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hbh
 */

@Data
public class UseTypeRelation extends BaseDO implements Serializable {
    private Long useTypeId;
    private Long templateId;
    private Float price;
    private static final long serialVersionUID = 1L;
}