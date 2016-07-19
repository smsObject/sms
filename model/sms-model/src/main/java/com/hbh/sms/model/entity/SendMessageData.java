package com.hbh.sms.model.entity;

import java.io.Serializable;

/**
 * Created by hbh on 2016/7/6.
 */
public class SendMessageData implements Serializable{

    /**接收者*/
    private  String receiver;
    /**消息体*/
    private String message ;
    /**发送状态*/
    private  boolean sendState ;

    public SendMessageData(String receiver , String message){
        this.receiver = receiver ;
        this.message = message ;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSendState() {
        return sendState;
    }

    public void setSendState(boolean sendState) {
        this.sendState = sendState;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
