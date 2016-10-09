package com.hbh.sms.service;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.SendMessageData;

/**
 * Created by hbh on 2016/7/6.
 */
public interface SendMessageService {

    public  boolean sendMessage(Concentrator concentrator , SendMessageData messageData);
}
