package com.sms.sendService;

import com.sms.sendService.entity.Concentrator;
import com.sms.sendService.entity.SendMessageData;

/**
 * Created by hbh on 2016/7/6.
 */
public interface SendMessageService {

    public  boolean sendMessage(Concentrator concentrator , SendMessageData messageData);
}
