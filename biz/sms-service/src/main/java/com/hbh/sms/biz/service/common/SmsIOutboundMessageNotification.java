package com.hbh.sms.biz.service.common;

import org.smslib.AGateway;
import org.smslib.IOutboundMessageNotification;
import org.smslib.OutboundMessage;

/**
 * Created by user on
 */
public class SmsIOutboundMessageNotification implements IOutboundMessageNotification {
    public  void process(AGateway var1, OutboundMessage var2){
        System.out.println("IOutboundMessageNotification .................");
    }
}
