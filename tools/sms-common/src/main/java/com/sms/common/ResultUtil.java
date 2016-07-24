package com.sms.common;

/**
 * Created by hbh on 2016/7/6.
 */
public class ResultUtil {

    public static <T> Result<T> newSuccessResult(T data){
        return  newSuccessResult(data,StateCode.SUCCESS.getMessage());
    }

    /**成功结果*/
    public static <T> Result<T> newSuccessResult(T data,Integer totalCount){
        return newResult(data,StateCode.SUCCESS , StateCode.SUCCESS.getMessage() ,totalCount);
    }

    public static <T>  Result<T> newSuccessResult(T data,String statusText){
        return newResult(data,StateCode.SUCCESS,statusText,null);
    }

    public static <T> Result<T> newFailedResult(StateCode stateCode){
        return newFailedResult(null,stateCode);
    }

    public static <T> Result<T> newFailedResult(StateCode stateCode ,String statusText){
        return newFailedResult(null ,stateCode , statusText);
    }

    public static <T> Result<T> newFailedResult(T data , StateCode stateCode){
        return newFailedResult(data, stateCode,stateCode.getMessage());
    }

    public static <T> Result<T> newFailedResult(T data,StateCode stateCode,String statusText){
        return newResult(data,stateCode,statusText,null);
    }

    public static <T> Result<T> newResult(T data,StateCode stateCode,String statusText,Integer totalCount){
        Result<T> result = new Result<T>();
        result.setData(data);
        result.setStatusText(statusText);
        result.setStateCode(stateCode);
        result.setTotalCount(totalCount);
        return  result;
    }

}