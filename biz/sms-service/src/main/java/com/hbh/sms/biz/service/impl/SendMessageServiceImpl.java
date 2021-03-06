package com.hbh.sms.biz.service.impl;

import com.hbh.sms.biz.service.DataService;
import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.biz.service.common.GatewayCenter;
import com.hbh.sms.biz.service.common.MessageCenter;
import com.hbh.sms.model.entity.SendMessageData;
import com.hbh.sms.biz.service.SendMessageService;
import org.smslib.modem.SerialModemGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hbh on 2016/7/6.
 */
@Service("sendMessageService")
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private DataService dataService;

    public boolean sendMessage(Concentrator concentrator , SendMessageData messageData) {
        //网关
        SerialModemGateway gateway= GatewayCenter.getGateway(concentrator);
        gateway.setInbound(true);   //设置true，表示该网关可以接收短信,根据需求修改
        gateway.setOutbound(true);//设置true，表示该网关可以发送短信,根据需求修改
        return MessageCenter.sendMessage(gateway , messageData ,dataService);
    }
}