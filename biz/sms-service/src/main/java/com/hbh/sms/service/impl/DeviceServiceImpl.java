package com.hbh.sms.service.impl;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.service.DeviceService;
import com.sms.common.Result;
import org.smslib.helper.CommPortIdentifier;
import org.smslib.helper.SerialPort;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

/**
 * Created by hbh on 2016/7/31.
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    private static CommPortIdentifier portId;
    static Enumeration portList;
    static int bauds[] = {9600, 19200, 57600, 115200};    //检测端口所支持的波特率

    public Result<Concentrator> scanner() {
        portList = CommPortIdentifier.getPortIdentifiers();
        System.out.println("短信设备端口连接测试...");
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            return null;
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                for (int i =0 ; i < bauds.length; i++) {
                    System.out.print("  Trying at " + bauds[i] + "...");
                    try {
                        System.out.println("找到串口: " + portId.getName());
                        SerialPort serialPort;
                        InputStream inStream;
                        OutputStream outStream;
                        int c;
                        String response;
                        serialPort = (SerialPort) portId.open("SMSLibCommTester", 1971);
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
                        serialPort.setSerialPortParams(bauds[i], SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                        inStream = serialPort.getInputStream();
                        outStream = serialPort.getOutputStream();
                        serialPort.enableReceiveTimeout(1000);
                        c = inStream.read();
                        while (c != -1)
                            c = inStream.read();
                        outStream.write('A');
                        outStream.write('T');
                        outStream.write('\r');
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                        response = "";
                        c = inStream.read();
                        while (c != -1) {
                            response += (char) c;
                            c = inStream.read();
                        }
                        if (response.indexOf("OK") >= 0) {
                            try {
                                System.out.print("  获取设备信息...");
                                outStream.write('A');
                                outStream.write('T');
                                outStream.write('+');
                                outStream.write('C');
                                outStream.write('G');
                                outStream.write('M');
                                outStream.write('M');
                                outStream.write('\r');
                                response = "";
                                c = inStream.read();
                                while (c != -1) {
                                    response += (char) c;
                                    c = inStream.read();
                                }

                                System.out.println("  发现设备: " + response.replaceAll("\\s+OK\\s+", "").replaceAll("\n", "").replaceAll("\r", ""));
                            } catch (Exception e) {

                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

}