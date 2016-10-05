package com.hbh.sms.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hbh on 2016/7/6.
 */
@Data
public class SendMessageData implements Serializable {

    /**
     * 接收者
     */
    private String receiver;
    /**
     * 消息体
     */
    private String message;
    /**
     * 发送状态
     */
    private boolean sendState;

    public SendMessageData(String receiver, String message) {
        this.receiver = receiver;
        this.message = message;
    }
}
