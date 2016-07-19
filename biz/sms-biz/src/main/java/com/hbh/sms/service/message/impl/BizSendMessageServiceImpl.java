package com.hbh.sms.service.message.impl;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.SendMessageData;
import com.hbh.sms.service.SendMessageService;
import com.hbh.sms.service.message.BizSendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hbh on 2016/7/17.
 */
@Service("bizSendMessageService")
public class BizSendMessageServiceImpl implements BizSendMessageService {

    @Autowired
    private SendMessageService sendMessageService;
    public boolean sendMessage(Concentrator concentrator, SendMessageData messageData) {
        return sendMessageService.sendMessage(concentrator , messageData);
    }
}
