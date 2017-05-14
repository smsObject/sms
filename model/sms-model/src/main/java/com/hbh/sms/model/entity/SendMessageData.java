package com.hbh.sms.model.entity;

import lombok.Data;
import java.io.Serializable;

/**
 * Created by hbh
 */
@Data
public class SendMessageData implements Serializable{
    private  String receiver;
    private String message ;
    private  boolean sendState ;
    public SendMessageData(String receiver , String message){
        this.receiver = receiver ;
        this.message = message ;
    }
}