package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hbh
 **/
@Data
public class SystemRoleMenu extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roleId;
    private Long menuId;
}