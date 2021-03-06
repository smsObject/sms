package com.hbh.sms.biz.service.common;

import com.hbh.sms.biz.service.DataService;
import org.smslib.*;
import org.smslib.helper.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by user on
 */
public class SmsIInboundMessageNotification implements IInboundMessageNotification {
    @Autowired
    private DataService dataService;

    SmsIInboundMessageNotification(DataService dataService){
        super();
        this.dataService = dataService;
    }

    @Override
    public void process(AGateway aGateway, Message.MessageTypes messageTypes, InboundMessage msg) {
        System.out.println("IInboundMessageNotification .................");
        System.out.println(msg.getText());
        try {
            if (msg.getText().length() != 42 && msg.getText().length() != 136 ){
                System.out.println("长度不正确的内容:"+msg.getText());
                return;
            }
            System.out.println(msg);
            dataService.parseGsmData(msg);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getInstance().logError("Error deleting received message!", e, null);
        }
    }
}