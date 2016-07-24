package com.hbh.sms.controllers;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.SendMessageData;
import com.hbh.sms.service.message.BizSendMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hbh on 2016/7/17.
 */

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private BizSendMessageService bizSendMessageService;

    @RequestMapping("meterManager")
    public String meterManager(){
        return "device/meterManager";
    }

    @RequestMapping("gsmManager")
    public String gsmManager(){
        return "device/gsmManager";
    }

    @RequestMapping("readMeterData")
    public boolean readMeterData(){
        //获取GSM信息  查询仪表id查询仪表数据
        //获取发送对象
        Concentrator concentrator = new Concentrator("COM3" , 19200 , "SIEMENS" ,"TC35i");
        SendMessageData messageData = new SendMessageData("18205815108" , "smsCenter message!");
        boolean b=false;
        long start=  System.currentTimeMillis();
        b= bizSendMessageService.sendMessage(concentrator , messageData);
        System.out.println(b);
        long time= System.currentTimeMillis() - start;
        System.out.println("耗时:"+time);
        return b;
    }
}