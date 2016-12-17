package com.sms.sendservice.test;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.biz.service.common.CyptoUtils;
import com.hbh.sms.biz.service.impl.ReadMessageServiceImpl;
import com.hbh.sms.biz.service.impl.SendMessageServiceImpl;
import com.hbh.sms.model.entity.SendMessageData;
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
        Concentrator concentrator = new Concentrator();
        concentrator.setComPort("COM4");
        concentrator.setBaudRate(19200);
        concentrator.setManufacturer("SIEMENS");
        concentrator.setModel("TC35i");
        SendMessageData messageData = new SendMessageData("15371508177" , "smsCenter message!");
        boolean b= sendMessageService.sendMessage(concentrator , messageData);
        System.out.print(b);
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

    public static void main(String[] args){
        short [] sends = new short[5];
        sends[0] = 0x00;
        sends[1] = 0x01;
        sends[2] = 0x06;
        Test.crc16(sends,3);
        System.out.println(Test.shotToHexString(sends));
    }

    public static void crc16(short[] sends,int size) {
        char temp = 0xffff;
        int i, j;
        for (j = 0; j < size; j++) {
            temp ^= sends[j];
            for (i = 0; i < 8; i++) {
                if ((temp & 0x01) > 0) {
                    temp >>= 1;
                    temp ^= 0xA001;
                } else {
                    temp >>= 1;
                }
            }
        }
        sends[size] = (short) ((temp & 0xFF00) >> 8);
        sends[size+1] = (short) (temp & 0x00FF);
    }

    public static String shotToHexString(short [] sends){
        StringBuffer stringBuffer = new StringBuffer();
        for (short i :sends){
            if (i< 10){
                stringBuffer.append("0"+Integer.toHexString(i));
            }else{
                stringBuffer.append(Integer.toHexString(i));
            }
        }
        return stringBuffer.toString();
    }
}