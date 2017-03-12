package com.hbh.sms.biz.service.job;

import org.smslib.*;

/**
 * Created by user on 2017/3/9.
 */
public class ReadMessageNotification implements IInboundMessageNotification {

    @Override
    public void process(AGateway aGateway, Message.MessageTypes messageTypes, InboundMessage inboundMessage) {
        inboundMessage.getText();
    }

    public static  void  main(String[] args){
        Service.getInstance().setInboundMessageNotification(new ReadMessageNotification());
    }
}