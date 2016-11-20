package com.hbh.sms.model.entity;

import com.sms.common.PagedQuery;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by hbh on 2016/7/20.
 */
@Data
public class BaseDO extends PagedQuery implements Serializable{
    private Long id;
    private Date    createDate;
    private Date    updateDate;
    private String  createUser;
    private String  updateUser;
}
