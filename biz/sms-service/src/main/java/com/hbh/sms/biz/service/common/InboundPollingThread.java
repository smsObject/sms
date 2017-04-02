package com.hbh.sms.biz.service.common;

import org.smslib.helper.Logger;
import org.smslib.smsserver.SMSServer;

/**
 * Created by user on 2017/4/2.
 */
public class InboundPollingThread extends Thread{

    InboundPollingThread() {
    }

    public void run() {
        while(true) {
            try {
                System.out.println("读取短信开始");
                MessageCenter.readMessages();
                Thread.sleep(2000);
            }catch (Exception var3) {
                Logger.getInstance().logDebug("Error in InboundPollingThread()", var3, (String)null);
            }
        }
    }
}
