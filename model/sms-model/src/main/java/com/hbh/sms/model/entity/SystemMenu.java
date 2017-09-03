package com.hbh.sms.model.entity;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hbh
 */

@Data
public class SystemMenu  extends BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String name;
    private Integer level;
    private Long parentId;
    private String permissionString;
    private List<SystemMenu> childs;
    private SystemMenu buttons;
}