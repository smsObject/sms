package com.hbh.sms.biz.service.impl;

import com.hbh.sms.biz.service.common.DataCenter;
import com.hbh.sms.biz.service.common.GatewayCenter;
import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.biz.service.common.DeviceCenter;
import com.hbh.sms.biz.service.DeviceService;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.smslib.AGateway;
import org.smslib.helper.CommPortIdentifier;
import org.smslib.helper.SerialPort;
import org.smslib.modem.SerialModemGateway;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/**
 * Created by hbh on 2016/7/31.
 */
@Service("deviceService")
public class DeviceServiceImpl extends Thread implements DeviceService {
    private static CommPortIdentifier portId;
    static Enumeration portList;
    static int bauds[] = {19200, 9600, 57600, 115200};    //检测端口所支持的波特率

    //AT+CGMM  TC35i AT+CGMI SIEMENS
    public Result<List<Concentrator>> scanner() {
        portList = CommPortIdentifier.getPortIdentifiers();
        System.out.println("短信设备检查中中……");
        List<Concentrator> concentrators = new ArrayList<Concentrator>();
        while (portList.hasMoreElements()) {
            portId = (CommPortIdentifier) portList.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println("找到串口: " + portId.getName());
                for (int i = 0; i < bauds.length; i++) {
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

                        DeviceCenter deviceCenter = new DeviceCenter(inStream, outStream);
                        boolean b = deviceCenter.isOnline();
                        if (b) {
                            deviceCenter.getDeviceByCmd("AT+CMGF=1");
                            String model = deviceCenter.getDeviceByCmd("AT+CGMM");
                            String manufacturer = deviceCenter.getDeviceByCmd("AT+CGMI");
                            Concentrator concentrator = DataCenter.concentrator;
                            concentrator.setComPort(portId.getName());
                            concentrator.setModel(model);
                            concentrator.setBaudRate(bauds[i]);
                            concentrator.setManufacturer(manufacturer);
                            concentrator.setIsOnline(1);//在线
                            concentrators.add(concentrator);
                            try {
                                if (serialPort != null) serialPort.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            //网关
                            SerialModemGateway gateway = GatewayCenter.getGateway(concentrator);
                            if (org.smslib.Service.getInstance().getGateways().size() == 0) {
                                org.smslib.Service.getInstance().addGateway(gateway);  //将网关添加到短信猫服务中
                            } else {
                                Iterator e = org.smslib.Service.getInstance().getGateways().iterator();
                                boolean c = true;
                                List<AGateway> remove = new ArrayList<>();
                                while (e.hasNext()) {
                                    SerialModemGateway serialModemGateway = (SerialModemGateway) e.next();
                                    if (gateway.getGatewayId().equals(serialModemGateway.getGatewayId())) {
                                        c = false;
                                        break;
                                    } else {
                                        remove.add(serialModemGateway);
                                    }
                                }
                                org.smslib.Service.getInstance().getGateways().removeAll(remove);
                                if (c) {
                                    org.smslib.Service.getInstance().addGateway(gateway);  //将网关添加到短信猫服务中
                                }
                            }

                            if (org.smslib.Service.getInstance().getServiceStatus() != org.smslib.Service.ServiceStatus.STARTED) {
                                System.out.println("正在启动服务。。。。。");
                                org.smslib.Service.getInstance().startService();   //启动服务，进入短信发送就绪状态
                                System.out.println("启动服务成功。。。。。");
                            }

                            System.out.println("检测完毕并返回!");
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("没有发现设备!");
                        return ResultUtil.newFailedResult(StateCode.ERROR);
                    } finally {
                        try {
                            if (serialPort != null) serialPort.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return ResultUtil.newSuccessResult(concentrators);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            Concentrator concentrator = DataCenter.concentrator;
            if (concentrator.getComPort() != null) {
                AGateway aGateway = org.smslib.Service.getInstance().getGateway(GatewayCenter.getId(concentrator));
                if (aGateway == null) {
                    concentrator.setIsOnline(0);
                } else {
                    AGateway.GatewayStatuses gatewayStatuses = aGateway.getStatus();
                    System.out.println(gatewayStatuses);
                    System.out.println();
                    if (gatewayStatuses != AGateway.GatewayStatuses.STOPPED
                            && gatewayStatuses != AGateway.GatewayStatuses.STARTING
                            && gatewayStatuses != AGateway.GatewayStatuses.STARTED
                            && gatewayStatuses != AGateway.GatewayStatuses.RESTART) {
                        try {
                            aGateway.stopGateway();
                            org.smslib.Service.getInstance().stopService();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        concentrator.setIsOnline(0);
                    }
                }
            }
            if (concentrator.getIsOnline() == null || concentrator.getIsOnline() != 1) {
                scanner();
            }
        }
    }

    public static void main(String[] args) {
        DeviceServiceImpl deviceService = new DeviceServiceImpl();
        Result<List<Concentrator>> result = deviceService.scanner();
        System.out.print(result.getData().get(0).getBaudRate());
        System.out.print("");
    }
}