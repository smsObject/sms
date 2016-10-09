package com.sms.sendservice.test;

import com.hbh.sms.biz.model.entity.Concentrator;
import com.hbh.sms.biz.service.common.CyptoUtils;
import com.hbh.sms.biz.service.impl.ReadMessageServiceImpl;
import com.hbh.sms.biz.service.impl.SendMessageServiceImpl;
import org.smslib.InboundMessage;

import java.util.List;

/**
 * Created by hbh on 2016/7/6.
 */

public class Test {
    //算法名称
    public static final String KEY_ALGORITHM = "DES";
    //算法名称/加密模式/填充方式
    //DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
    public static final String CIPHER_ALGORITHM = "DES/ECB/NoPadding";

    private static String hexStr = "0123456789ABCDEF";

    @org.junit.Test
    public void testSend() {
        SendMessageServiceImpl sendMessageService = new SendMessageServiceImpl();
//        Concentrator concentrator = new Concentrator("COM3" , 19200 , "SIEMENS" ,"TC35i");
//        SendMessageData messageData = new SendMessageData("18205815108" , "smsCenter message!");
//        boolean b= sendMessageService.sendMessage(concentrator , messageData);
//        System.out.print(b);
    }

    @org.junit.Test
    public void testRead() {
        ReadMessageServiceImpl readMessageService = new ReadMessageServiceImpl();
        Concentrator concentrator = new Concentrator();
        concentrator.setBaudRate(19200);
        concentrator.setComPort("COM4");
        concentrator.setManufacturer("SIEMENS");
        concentrator.setModel("TC35i");
        long t = System.currentTimeMillis();
        try {
            List<InboundMessage> results = readMessageService.readMessage(concentrator, InboundMessage.MessageClasses.ALL);
//        for (InboundMessage msg : results){
//            System.out.println(msg);
//        }
            long l = System.currentTimeMillis();
            System.out.println(l - t);
            List<InboundMessage> results1 = readMessageService.readMessage(concentrator, InboundMessage.MessageClasses.ALL);
            System.out.println("耗时" + (System.currentTimeMillis() - l));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testDES() {
        String source = "87061510112300306958000000000000";
        String key = "3C62795F6B736C3E";
        System.out.println("原文:" + source);
        System.out.println("key :" + key);
        String encodeStr = CyptoUtils.encode(key, source);
        System.out.println("加密后：" + encodeStr);
        System.out.println("解密后: " + CyptoUtils.decode(key, encodeStr));
    }

}