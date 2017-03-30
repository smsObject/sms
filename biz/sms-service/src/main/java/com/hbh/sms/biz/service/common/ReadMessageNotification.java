package com.hbh.sms.biz.service.common;

import org.smslib.*;

/**
 * Created by user on 2017/3/9.
 */
public class ReadMessageNotification implements IInboundMessageNotification {
//@Autowired
//private MeterDataService meterDataService;

    @Override
    public void process(AGateway aGateway, Message.MessageTypes messageTypes, InboundMessage inboundMessage) {
        System.out.println(messageTypes);
        System.out.println("时间2:"+System.currentTimeMillis());
      if (inboundMessage.getDCSMessageClass().equals(InboundMessage.MessageClasses.UNREAD)){
          System.out.println("时间:"+System.currentTimeMillis());
          System.out.println("内容:"+inboundMessage.getText());
          }
        //拿到数据,解析数据
        //存入数据库
        //Result<MeterData> meterDataResult = DataCenter.parseReadMeterData(inboundMessage.getText());
        //meterDataService.insert(meterDataResult.getData());
    }

    public static  void  main(String[] args){
        Service.getInstance().setInboundMessageNotification(new ReadMessageNotification());
    }
}