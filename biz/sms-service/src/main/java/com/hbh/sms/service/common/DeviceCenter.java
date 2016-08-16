package com.hbh.sms.service.common;

import com.hbh.sms.service.DeviceService;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by hbh on 2016/8/5.
 */
public class DeviceCenter {

    private InputStream inStream;

    private OutputStream outStream;

    public DeviceCenter (InputStream inStream , OutputStream outStream ){
        this.inStream = inStream ;
        this.outStream = outStream ;
    }

    public boolean isOnline() throws  Exception{
        int c;
        String response = "";
        c = this.inStream.read();
        while (c != -1)  c = inStream.read();
        outStream.write('A');
        outStream.write('T');
        outStream.write('\r');
        Thread.sleep(1000);
        response = "";
        c = inStream.read();
        while (c != -1)
        {
            response += (char) c;
            c = inStream.read();
        }
        if (response.indexOf("OK") >= 0){
            return true;
        }else {
            return  false;
        }
    }

    public String getDeviceByCmd(String cmd) throws Exception{
        int by;
        String response = "";
        for (char c : cmd.toCharArray()){
            outStream.write(c);
        }
        outStream.write('\r');
        response = "";
        by = inStream.read();
        while (by != -1)
        {
            response += (char) by;
            by = inStream.read();
        }
        return  response.replace(cmd , "").replaceAll("\\s+OK\\s+", "").replaceAll("\n", "").replaceAll("\r", "");
    }
}