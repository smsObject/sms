package com.hbh.sms.biz.service.common;

import com.hbh.sms.model.entity.MeterData;
import com.sms.common.Result;
import org.smslib.*;
import org.smslib.helper.Logger;

/**
 * Created by user on 2017/3/9.
 */
public class SmsIInboundMessageNotification implements IInboundMessageNotification {

    @Override
    public void process(AGateway aGateway, Message.MessageTypes messageTypes, InboundMessage msg) {
        System.out.println("IInboundMessageNotification .................");
        try {
            System.out.println(msg);
            Result<MeterData> result = DataCenter.parseReadMeterData(msg.getText());
            result.getData();
            msg.getOriginator();
            Service.getInstance().deleteMessage(msg);
        } catch (Exception e) {
            Logger.getInstance().logError("Error deleting received message!", e, null);
        }
    }

    public static void main(String[] args) {
        Service.getInstance().setInboundMessageNotification(new SmsIInboundMessageNotification());
    }
}