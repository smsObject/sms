package com.sms.common;

import lombok.Data;

import java.util.List;

/**
 * Created by hbh on 2016/7/6.
 */

@Data
public class Result<T> {
    private static final long serialVersionUID = -8004892467677559723L;
    /* 结果数据 */
    private T data;
    /* 状态码 */
    private StateCode stateCode;
    /* 状态描述辅助信息 */
    private String statusText;

    public  boolean isSuccess(){
        if (this.stateCode.getCode() == 1) return  true;
        else return false;
    }
}