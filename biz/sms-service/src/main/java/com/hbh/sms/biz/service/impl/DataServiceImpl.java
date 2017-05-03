package com.hbh.sms.biz.service.impl;

import com.hbh.sms.biz.service.DataService;
import com.hbh.sms.biz.service.common.DataCenter;
import com.hbh.sms.dal.dao.MeterDataMapper;
import com.hbh.sms.dal.dao.MeterMapper;
import com.hbh.sms.model.entity.Meter;
import com.hbh.sms.model.entity.MeterData;
import com.sms.common.Result;
import org.smslib.InboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author hbh
 * @create 2017-04-30 23:21
 **/
@Service
public class DataServiceImpl implements DataService {
    @Resource
    private MeterMapper meterMapper;

    @Autowired
    private MeterDataMapper meterDataMapper;

    @Override
    public void parseGsmData(InboundMessage msg) {
//        将数据写到日志表
        try {
            Date smsDate = msg.getDate();
            Result<MeterData> result = DataCenter.parseReadMeterData(msg.getText());
            if (result.isSuccess()) {
                MeterData meterData = result.getData();
                System.out.println("水表读数:" + meterData.getValue());
                meterData.setCode(msg.getOriginator());
                //查询仪表数据
                Meter meter = new Meter();
                meter.setMeterCode(msg.getOriginator());
                List<Meter> meters = meterMapper.query(meter);
                Long meterId = 0L;
                if (!meters.isEmpty()) {
                    Meter meter1 = meters.get(0);
                    meterId = meter1.getId();
                }
                meterData.setMeterId(meterId);
                meterData.setUnit("吨");
                meterData.setSmsDate(smsDate);
                List<MeterData> meterDatas = meterDataMapper.query(meterData);
                if (meterDatas.isEmpty()) {

                    int i = meterDataMapper.insert(meterData);
                    meter.setLastUpLoadTime(meterData.getSmsDate());
                    meter.setLastValveStatus(meterData.getValveStatus());
                    meter.setLastValue(meterData.getValue());
                    meterMapper.updateLastByCode(meter);

                    if (i > 0) {
                        org.smslib.Service.getInstance().deleteMessage(msg);
                    }
                } else {
                    org.smslib.Service.getInstance().deleteMessage(msg);
                }
            } else {
                System.out.println(result.getStatusText());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}