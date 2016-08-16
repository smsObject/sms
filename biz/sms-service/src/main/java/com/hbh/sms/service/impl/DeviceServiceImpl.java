package com.hbh.sms.service.impl;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.service.DeviceService;
import com.hbh.sms.service.common.DeviceCenter;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import org.smslib.helper.CommPortIdentifier;
import org.smslib.helper.SerialPort;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by hbh on 2016/7/31.
 */
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
    private static CommPortIdentifier portId;
    static Enumeration portList;
    static int bauds[] = {9600, 19200, 57600, 115200};    //检测端口所支持的波特率
    //AT+CGMM  TC35i AT+CGMI SIEMENS
    public Result<List<Concentrator>> scanner() {
        portList = CommPortIdentifier.getPortIdentifiers();
        System.out.println("短信设备检查中中……");
        List<Concentrator> concentrators = new ArrayList<Concentrator>() ;
        while (portList.hasMoreElements()){
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
                System.out.println("找到串口: "+portId.getName());
                for (int i =0 ; i <bauds.length ; i++){
                    System.out.print("  Trying at " + bauds[i] + "...");
                    SerialPort serialPort = null;
                    InputStream inStream;
                    OutputStream outStream;
                    try {
                        serialPort = (SerialPort) portId.open("SMSLibCommTester", 1971);
                        serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN);
                        serialPort.setSerialPortParams(bauds[i], SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                        inStream = serialPort.getInputStream();
                        outStream = serialPort.getOutputStream();
                        serialPort.enableReceiveTimeout(1000);


                        DeviceCenter deviceCenter = new DeviceCenter(inStream , outStream);
                        boolean b =deviceCenter.isOnline();
                        if (b){
                            String model = deviceCenter.getDeviceByCmd("AT+CGMM");
                            String manufacturer = deviceCenter.getDeviceByCmd("AT+CGMI");
                            Concentrator concentrator = new Concentrator() ;
                            concentrator.setComPort(portId.getName());
                            concentrator.setModel(model);
                            concentrator.setBaudRate(bauds[i]);
                            concentrator.setManufacturer(manufacturer);
                            concentrators.add(concentrator);
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("  没有发现设备!");
                    }finally {
                        try {
                            if (serialPort != null)
                                serialPort.close();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
        return ResultUtil.newSuccessResult(concentrators);
    }

    public static void main (String[] args){
        DeviceServiceImpl deviceService= new DeviceServiceImpl();
        Result<List<Concentrator>> result = deviceService.scanner();
        System.out.print(result.getData().get(0).getBaudRate());
        System.out.print("");
    }
}