package com.hbh.sms.biz.service.common;

//import com.hbh.sms.biz.service.job.SmsIInboundMessageNotification;

import com.hbh.sms.model.entity.SendMessageData;
import org.smslib.InboundMessage;
import org.smslib.Message;
import org.smslib.OutboundMessage;
import org.smslib.Service;
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

    public static void readMessages(){
        try {
            ArrayList msgList = new ArrayList();
            int size = Service.getInstance().readMessages(msgList, InboundMessage.MessageClasses.ALL);
            Iterator e =msgList.iterator();
            while (e.hasNext()){
                InboundMessage msg1 = (InboundMessage)e.next();
                System.out.println(msg1);
                Thread.sleep(1000);
//                Service.getInstance().deleteMessage(msg1);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static boolean readMeterData(SendMessageData sendMessageData) {
        return true;
    }

    private static void initServer(SerialModemGateway gateway) throws Exception {
        if (Service.getInstance().getServiceStatus() != Service.ServiceStatus.STARTED) {
            if(Service.getInstance().getInboundMessageNotification()  == null){
                SmsIInboundMessageNotification smsIInboundMessageNotification = new SmsIInboundMessageNotification();
                Service.getInstance().setInboundMessageNotification(smsIInboundMessageNotification);
            }
            if (Service.getInstance().getGateways().size() == 0) {
                Service.getInstance().addGateway(gateway);  //将网关添加到短信猫服务中
            }
            Service.getInstance().startService();   //启动服务，进入短信发送就绪状态
//            new InboundPollingThread().start();
        }
    }

    public static void main(String[] args){
        String id = "hbh.applicatioin";
        SerialModemGateway gateway = new SerialModemGateway(id,"COM5",19200,"SIEMENS","TC35i");
        gateway.setInbound(true);
        try {
            initServer(gateway);
            System.in.read();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}