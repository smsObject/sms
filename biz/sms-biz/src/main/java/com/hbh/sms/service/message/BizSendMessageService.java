package com.hbh.sms.service.message;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.SendMessageData;

/**
 * Created by hbh on 2016/7/17.
 */
public interface BizSendMessageService {

    public  boolean sendMessage(Concentrator concentrator , SendMessageData messageData);

}
