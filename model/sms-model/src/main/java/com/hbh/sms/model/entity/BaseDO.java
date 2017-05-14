package com.hbh.sms.model.entity;

import com.sms.common.PagedQuery;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hbh
 */
@Data
public class BaseDO extends PagedQuery implements Serializable {
    private Long id;
    private Date createTime;
    private Date updateTime;
    private String operator;
    private String createPerson;
    private String updatePerson;
}