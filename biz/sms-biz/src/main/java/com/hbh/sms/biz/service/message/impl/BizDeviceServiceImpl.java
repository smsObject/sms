package com.hbh.sms.biz.service.message.impl;

import com.hbh.sms.biz.model.entity.Concentrator;
import com.hbh.sms.biz.service.message.BizDeviceService;
import com.hbh.sms.biz.model.entity.SendMessageData;
import com.hbh.sms.biz.service.DeviceService;
import com.hbh.sms.biz.service.SendMessageService;
import com.sms.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hbh on 2016/7/17.
 */
@Service("bizDeviceService")
public class BizDeviceServiceImpl implements BizDeviceService {

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private DeviceService deviceService;

    public boolean sendMessage(Concentrator concentrator, SendMessageData messageData) {
        return sendMessageService.sendMessage(concentrator , messageData);
    }

    public Result<List<Concentrator>> scanner() {
        return deviceService.scanner();
    }

}
