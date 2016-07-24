package com.sms.common;

import java.util.List;

/**
 * Created by hbh on 2016/7/6.
 */

public class Result<T> {
    private static final long serialVersionUID = -8004892467677559723L;
    /* 结果数据 */
    private T data;
    /** 返回数据总条数 */
    private Integer totalCount;
    /* 状态码 */
    private StateCode stateCode;
    /* 状态描述辅助信息 */
    private String statusText;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}
