package com.hbh.sms.service.impl;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.service.ReadMessageService;
import com.hbh.sms.service.common.GatewayCenter;
import com.hbh.sms.service.common.MessageCenter;
import org.smslib.InboundMessage;
import org.smslib.modem.SerialModemGateway;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hbh on 2016/7/7.
 */
@Service("readMessageService")
public class ReadMessageServiceImpl implements ReadMessageService {

    public List<InboundMessage> readMessage(Concentrator concentrator , InboundMessage.MessageClasses messageClasses) throws  Exception {
        //网关
        SerialModemGateway gateway= GatewayCenter.getGateway(concentrator);
        gateway.setInbound(true);   //设置true，表示该网关可以接收短信,根据需求修改

        return  MessageCenter.readMessage(gateway ,messageClasses);
    }
}