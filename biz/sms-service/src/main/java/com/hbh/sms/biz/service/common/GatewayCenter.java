package com.hbh.sms.biz.service.common;

import com.hbh.sms.model.entity.Concentrator;
import org.smslib.modem.SerialModemGateway;

/**
 * Created by hbh on 2016/7/6.
 */

public class GatewayCenter {

    public static SerialModemGateway getGateway(Concentrator concentrator) {
        SerialModemGateway serialModemGateway = new SerialModemGateway(getId(concentrator), concentrator.getComPort(), concentrator.getBaudRate()
                , concentrator.getManufacturer(), concentrator.getModel());
        serialModemGateway.setInbound(true);
        serialModemGateway.setOutbound(true);
        return serialModemGateway;
    }

    private static String getId(Concentrator concentrator) {
        return concentrator.getComPort() + concentrator.getBaudRate() + concentrator.getManufacturer() + concentrator.getModel();
    }

}