package com.hbh.sms.biz.service.common;

import org.smslib.AGateway;
import org.smslib.IGatewayStatusNotification;

/**
 * Created by user on
 */
public class SmsIGatewayStatusNotification implements IGatewayStatusNotification {

    public void process(AGateway aGateway, AGateway.GatewayStatuses gatewayStatuses, AGateway.GatewayStatuses gatewayStatuses1){
        System.out.println("IGatewayStatusNotification ....................");
    }
}
