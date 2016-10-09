package com.hbh.sms.biz.service;

import com.hbh.sms.biz.model.entity.Concentrator;
import com.hbh.sms.biz.model.entity.SendMessageData;

/**
 * Created by hbh on 2016/7/6.
 */
public interface SendMessageService {

    public  boolean sendMessage(Concentrator concentrator , SendMessageData messageData);
}
