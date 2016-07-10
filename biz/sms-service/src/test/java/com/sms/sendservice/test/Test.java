package com.sms.sendservice.test;

import com.sms.sendService.common.CyptoUtils;
import com.sms.sendService.entity.Concentrator;
import com.sms.sendService.entity.SendMessageData;
import com.sms.sendService.impl.ReadMessageServiceImpl;
import com.sms.sendService.impl.SendMessageServiceImpl;
import org.apache.commons.codec.binary.Base64;
import org.smslib.InboundMessage;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;
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

    private static String hexStr =  "0123456789ABCDEF";

    @org.junit.Test
    public  void  testSend(){
        SendMessageServiceImpl sendMessageService =new SendMessageServiceImpl();
        Concentrator concentrator = new Concentrator("COM3" , 19200 , "SIEMENS" ,"TC35i");
        SendMessageData messageData = new SendMessageData("18205815108" , "smsCenter message!");
        boolean b= sendMessageService.sendMessage(concentrator , messageData);
        System.out.print(b);
    }

    @org.junit.Test
    public void testRead(){
        ReadMessageServiceImpl readMessageService =new ReadMessageServiceImpl();
        Concentrator concentrator = new Concentrator("COM3" , 19200 , "SIEMENS" ,"TC35i");
        List<InboundMessage> results= readMessageService.readMessage(concentrator);
        for (InboundMessage msg : results){
            System.out.println(msg);
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