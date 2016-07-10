package com.sms.common;

import java.util.List;

/**
 * Created by hbh on 2016/7/6.
 */

public class Result<T> {
    private  T data;
    private List<T> dateList;
    private StateCode stateCode;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDateList() {
        return dateList;
    }

    public void setDateList(List<T> dateList) {
        this.dateList = dateList;
    }

    public StateCode getStateCode() {
        return stateCode;
    }

    public void setStateCode(StateCode stateCode) {
        this.stateCode = stateCode;
    }
}
