package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author hbh
 **/
@Data
public class SystemUserRole extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long userId;
    private Long roleId;
}