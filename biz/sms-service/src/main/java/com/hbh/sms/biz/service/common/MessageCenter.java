package com.hbh.sms.biz.service.common;

import com.hbh.sms.model.entity.SendMessageData;
import org.smslib.*;
import org.smslib.modem.SerialModemGateway;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hbh on 2016/7/6.
 */
public class MessageCenter {
    public static boolean sendMessage(SerialModemGateway gateway, SendMessageData messageData) {
        boolean b = false;
        try {
            initServer(gateway);
            OutboundMessage msg = new OutboundMessage(messageData.getReceiver(), messageData.getMessage());
            msg.setEncoding(Message.MessageEncodings.ENCUCS2);
            //b = Service.getInstance().sendMessage(msg); //执行发送短信
        } catch (Exception ex) {
            ex.printStackTrace();
            b = false;
        }
        return b;
    }

    public static List<InboundMessage> readMessage(SerialModemGateway gateway, InboundMessage.MessageClasses messageClasses) throws Exception {
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

    private static void initServer(SerialModemGateway gateway) throws Exception {
        if(Service.getInstance().getInboundMessageNotification()  == null){
            SmsIInboundMessageNotification smsIInboundMessageNotification = new SmsIInboundMessageNotification();
            Service.getInstance().setInboundMessageNotification(smsIInboundMessageNotification);
        }

        if (Service.getInstance().getOutboundMessageNotification() == null){
            SmsIOutboundMessageNotification  smsIOutboundMessageNotification = new SmsIOutboundMessageNotification();
            Service.getInstance().setOutboundMessageNotification(smsIOutboundMessageNotification);
        }

        if (Service.getInstance().getGateways().size() == 0) {
            Service.getInstance().addGateway(gateway);  //将网关添加到短信猫服务中
        }else {
            Iterator e = Service.getInstance().getGateways().iterator();
            boolean b = true;
            while (e.hasNext()){
                SerialModemGateway serialModemGateway = (SerialModemGateway)e.next();
                if (gateway.getGatewayId().equals(serialModemGateway.getGatewayId())){
                    b = false;
                    return;
                }
            }
            if (b){
                Service.getInstance().addGateway(gateway);  //将网关添加到短信猫服务中
            }
        }

        if (Service.getInstance().getServiceStatus() != Service.ServiceStatus.STARTED) {
            System.out.println("正在启动服务。。。。。");
            Service.getInstance().startService();   //启动服务，进入短信发送就绪状态
            System.out.println("启动服务成功。。。。。");
        }
    }

    public static void main(String[] args){
        String id = "COM519200SIEMENSTC35i";
        SerialModemGateway gateway = new SerialModemGateway(id,"COM5",19200,"SIEMENS","TC35i");
        gateway.setInbound(true);
        try {
            initServer(gateway);
            initServer(gateway);
            System.in.read();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}