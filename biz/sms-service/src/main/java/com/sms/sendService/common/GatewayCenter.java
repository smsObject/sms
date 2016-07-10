package com.sms.sendService.common;

import com.sms.sendService.entity.Concentrator;
import org.smslib.modem.SerialModemGateway;

/**
 * Created by hbh on 2016/7/6.
 */
public class GatewayCenter {
    private final static String id = "hbh.applicatioin";

    public static SerialModemGateway getGateway(Concentrator concentrator){
        return  new SerialModemGateway(id, concentrator.getComPort() ,concentrator.getBaudRate()
                ,concentrator.getManufacturer(), concentrator.getModel());
    }

}
