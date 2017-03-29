package com.hbh.sms.biz.service.job;

import com.hbh.sms.biz.service.MeterData.MeterDataService;
import com.hbh.sms.biz.service.common.DataCenter;
import com.hbh.sms.model.entity.MeterData;
import com.sms.common.Result;
import org.smslib.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by user on 2017/3/9.
 */
public class ReadMessageNotification implements IInboundMessageNotification {
@Autowired
private MeterDataService meterDataService;
    @Override
    public void process(AGateway aGateway, Message.MessageTypes messageTypes, InboundMessage inboundMessage) {
        inboundMessage.getText();
        //拿到数据,解析数据
        //存入数据库
        Result<MeterData> meterDataResult = DataCenter.parseReadMeterData(inboundMessage.getText());
        meterDataService.insert(meterDataResult.getData());
    }

    public static  void  main(String[] args){
        Service.getInstance().setInboundMessageNotification(new ReadMessageNotification());
    }
}