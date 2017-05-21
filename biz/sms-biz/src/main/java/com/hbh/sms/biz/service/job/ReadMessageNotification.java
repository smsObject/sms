package com.hbh.sms.biz.service.job;

import org.smslib.*;

/**
 * Created by user on
 */
public class ReadMessageNotification implements IInboundMessageNotification {
//@Autowired
//private MeterDataService meterDataService;
    @Override
    public void process(AGateway aGateway, Message.MessageTypes messageTypes, InboundMessage inboundMessage) {
        inboundMessage.getText();
        //拿到数据,解析数据
        //存入数据库
        //Result<meterData> meterDataResult = DataCenter.parseReadMeterData(inboundMessage.getText());
        //meterDataService.insert(meterDataResult.getData());
    }

    public static  void  main(String[] args){
        Service.getInstance().setInboundMessageNotification(new ReadMessageNotification());
    }
}