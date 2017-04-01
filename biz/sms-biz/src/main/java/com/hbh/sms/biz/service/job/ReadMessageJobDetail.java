package com.hbh.sms.biz.service.job;

import com.hbh.sms.model.entity.Concentrator;
//import com.hbh.sms.biz.service.ReadMessageService;
import com.hbh.sms.biz.service.concentrator.ConcentratorService;
import com.sms.common.Result;
import org.smslib.InboundMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hbh on 2016/8/28.
 */

public class ReadMessageJobDetail {

//    @Autowired
//    private ReadMessageService readMessageService;
    @Autowired
    private ConcentratorService concentratorService;

    public void readMessage(){
        System.out.println("--------------读取信息任务开始-------------");
        Result<List<Concentrator>> result =concentratorService.list(new Concentrator());
        if (result.isSuccess()){
            for (Concentrator concentrator :result.getData()){
                System.out.println("任务执行中。。。。。。");
                List<InboundMessage> listMessage = null;
                try{
//                  listMessage=readMessageService.readMessage(concentrator, InboundMessage.MessageClasses.ALL);
                    System.out.println("数据"+listMessage.size());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }else {
            System.out.println("数据库查询失败!");
        }
        System.out.println("--------------读取信息任务结束-------------");
    }
}