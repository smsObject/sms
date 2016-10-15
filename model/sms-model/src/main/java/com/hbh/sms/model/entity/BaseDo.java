package com.hbh.sms.model.entity;

import com.sms.common.Page;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hbh on 2016/7/20.
 */
@Data
public class BaseDo extends Page implements Serializable{
    private Integer id;
    private Date    createDate;
    private Date    updateDate;
    private String  createUser;
    private String  updateUser;
}
