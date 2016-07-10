package com.sms.sendService.impl;

import com.sms.sendService.SendMessageService;
import com.sms.sendService.common.GatewayCenter;
import com.sms.sendService.common.MessageCenter;
import com.sms.sendService.entity.Concentrator;
import com.sms.sendService.entity.SendMessageData;
import org.smslib.modem.SerialModemGateway;

/**
 * Created by hbh on 2016/7/6.
 */
public class SendMessageServiceImpl implements SendMessageService {

    public boolean sendMessage(Concentrator concentrator , SendMessageData messageData) {
        messageData.getReceiver();
        messageData.getMessage();
        //网关
        SerialModemGateway gateway= GatewayCenter.getGateway(concentrator);
        //gateway.setInbound(true);   //设置true，表示该网关可以接收短信,根据需求修改
        gateway.setOutbound(true);//设置true，表示该网关可以发送短信,根据需求修改
        return MessageCenter.sendMessage(gateway , messageData );
    }
}