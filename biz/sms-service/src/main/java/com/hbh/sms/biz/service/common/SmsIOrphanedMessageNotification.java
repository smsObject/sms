package com.hbh.sms.biz.service.common;

import org.smslib.AGateway;
import org.smslib.IOrphanedMessageNotification;
import org.smslib.InboundMessage;

/**
 * Created by user on 2017/4/2.
 */
public class SmsIOrphanedMessageNotification implements IOrphanedMessageNotification {

    @Override
    public boolean process(AGateway aGateway, InboundMessage inboundMessage){
        System.out.println("IOrphanedMessageNotification ....................");
        return false;
    }
}