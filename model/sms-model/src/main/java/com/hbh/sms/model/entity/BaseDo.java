package com.hbh.sms.model.entity;

import com.sms.common.Page;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hbh on 2016/7/20.
 */
public class BaseDo extends Page implements Serializable{
    private Integer id;
    private Date    createDate;
    private Date    updateDate;
    private String  createUser;
    private String  updateUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}
