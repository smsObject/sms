package com.sms.sendService.common;

import com.sms.sendService.entity.SendMessageData;
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
    public static boolean sendMessage(SerialModemGateway gateway , SendMessageData messageData){
        boolean b =false;
        try {
            Service.getInstance().addGateway(gateway);  //将网关添加到短信猫服务中
            Service.getInstance().startService();   //启动服务，进入短信发送就绪状态
            OutboundMessage msg = new OutboundMessage(messageData.getReceiver() , messageData.getMessage());
            msg.setEncoding(Message.MessageEncodings.ENCUCS2);
            b= Service.getInstance().sendMessage(msg); //执行发送短信
            Service.getInstance().stopService();
        }catch (Exception ex){
            ex.printStackTrace();
            b=false;
        }
        return  b;
    }

    public static List<InboundMessage>  readMessage(SerialModemGateway gateway){
            try {
                List<InboundMessage> msgList = new ArrayList<InboundMessage>();
                Service.getInstance().addGateway(gateway);
                Service.getInstance().startService();
                Service.getInstance().readMessages(msgList, InboundMessage.MessageClasses.UNREAD);
                return msgList;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        return null;
    }
}
