package com.hbh.sms.biz.service.common;

import com.hbh.sms.model.entity.SendMessageData;
import org.smslib.InboundMessage;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.Service;
import org.smslib.modem.SerialModemGateway;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbh on 2016/7/6.
 */
public class MessageCenter {
    public static boolean sendMessage(SerialModemGateway gateway, SendMessageData messageData) {
        boolean b = false;
        try {
            if (Service.getInstance().getServiceStatus() != Service.ServiceStatus.STARTED) {
                Service.getInstance().addGateway(gateway);  //将网关添加到短信猫服务中
                Service.getInstance().startService();   //启动服务，进入短信发送就绪状态
            }
            OutboundMessage msg = new OutboundMessage(messageData.getReceiver(), messageData.getMessage());
            msg.setEncoding(Message.MessageEncodings.ENCUCS2);
            b = Service.getInstance().sendMessage(msg); //执行发送短信
            // Service.getInstance().stopService();
        } catch (Exception ex) {
            ex.printStackTrace();
            b = false;
        }
        return b;
    }

    public static List<InboundMessage> readMessage(SerialModemGateway gateway, InboundMessage.MessageClasses messageClasses) throws Exception{
        List<InboundMessage> msgList = new ArrayList<InboundMessage>();
        System.out.println(Service.getInstance().getServiceStatus());
        if (Service.getInstance().getServiceStatus() == Service.ServiceStatus.STOPPED) {
            Service.getInstance().addGateway(gateway);  //将网关添加到短信猫服务中
            Service.getInstance().startService();   //启动服务，进入短信发送就绪状态
        }
        Service.getInstance().readMessages(msgList, messageClasses);
        return msgList;
    }

    public static boolean readMeterData(SendMessageData sendMessageData) {

        return true;
    }


}
