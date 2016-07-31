package com.hbh.sms.service.message.impl;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.SendMessageData;
import com.hbh.sms.service.SendMessageService;
import com.hbh.sms.service.message.BizDeviceService;
import com.sms.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hbh on 2016/7/17.
 */
@Service("bizDeviceService")
public class BizDeviceServiceImpl implements BizDeviceService {

    @Autowired
    private SendMessageService sendMessageService;
    public boolean sendMessage(Concentrator concentrator, SendMessageData messageData) {
        return sendMessageService.sendMessage(concentrator , messageData);
    }

    public Result<Concentrator> scanner() {
        return null;
    }
}
