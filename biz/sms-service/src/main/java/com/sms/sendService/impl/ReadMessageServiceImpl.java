package com.sms.sendService.impl;

import com.sms.sendService.ReadMessageService;
import com.sms.sendService.common.GatewayCenter;
import com.sms.sendService.common.MessageCenter;
import com.sms.sendService.entity.Concentrator;
import org.smslib.InboundMessage;
import org.smslib.modem.SerialModemGateway;

import java.util.List;

/**
 * Created by hbh on 2016/7/7.
 */
public class ReadMessageServiceImpl implements ReadMessageService {

    public List<InboundMessage> readMessage(Concentrator concentrator) {
        //网关
        SerialModemGateway gateway= GatewayCenter.getGateway(concentrator);
        gateway.setInbound(true);   //设置true，表示该网关可以接收短信,根据需求修改
        return MessageCenter.readMessage(gateway);
    }

}
