package com.sms.common;

/**
 * Created by hbh on 2016/7/6.
 */
public enum StateCode {

    SUCCESS(1 , "成功"),
    ERROR(-1 ,"请求失败"),
    PARAMETERS_FAILED(-2,"参数不全");

    private int code ;
    private String message ;

    private StateCode (int code ,String message){
        this.code=code;
        this.message=message;
    }

    public int getCode(){
        return  this.code ;
    }

    public  String getMessage(){
        return this.message ;
    }
}
