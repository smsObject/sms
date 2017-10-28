package com.hbh.sms.biz.service.impl;

import com.hbh.sms.biz.service.DataService;
import com.hbh.sms.biz.service.common.DataCenter;
import com.hbh.sms.dal.dao.MeterDataMapper;
import com.hbh.sms.dal.dao.MeterMapper;
import com.hbh.sms.dal.dao.UserInfoMapper;
import com.hbh.sms.model.entity.Meter;
import com.hbh.sms.model.entity.MeterData;
import com.hbh.sms.model.entity.SmsData;
import com.hbh.sms.model.entity.UserInfo;
import com.sms.common.Result;
import org.smslib.InboundMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author hbh
 * @create
 **/
@Service
public class DataServiceImpl implements DataService {
    @Resource
    private MeterMapper meterMapper;
    @Autowired
    private MeterDataMapper meterDataMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void parseGsmData(InboundMessage msg) {
//        将数据写到日志表
        try {
            Result<SmsData> result = DataCenter.parseSmsData(msg.getText());
            if (result.isSuccess()) {
                SmsData smsData = result.getData();
                if (smsData.getMeter() != null) {
                    parseMeter(smsData.getMeter(), msg);
                }
                if (smsData.getMeterData() != null) {
                    parseMeterData(smsData.getMeterData(), msg);
                }
            } else {
                System.out.println(result.getStatusText());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void parseMeter(Meter meter, InboundMessage msg) throws Exception {
        meter.setMeterCode(msg.getOriginator());
        List<Meter> meters = meterMapper.query(meter);
        Long meterId = 0L;
        if (!meters.isEmpty()) {
            Meter meter1 = meters.get(0);
            meterId = meter1.getId();
        }
        meter.setId(meterId);
        int i = meterMapper.updateSettingById(meter);
        if (i > 0) {
            org.smslib.Service.getInstance().deleteMessage(msg);
        }
    }

    @Transactional
    private void parseMeterData(MeterData meterData, InboundMessage msg) throws Exception {
        Date smsDate = msg.getDate();
        meterData.setCode(msg.getOriginator());
        //查询仪表数据
        Meter meterForSearch = new Meter();
        meterForSearch.setMeterCode(msg.getOriginator());
        List<Meter> meters = meterMapper.query(meterForSearch);
        Meter meter = null;
        if (meters.isEmpty()) {
            return;
        }

        meter = meters.get(0);
        meterData.setMeterId(meter.getId());
        meterData.setUnit(meter.getUnit());
        meterData.setSmsDate(smsDate);
        List<MeterData> meterDatas = meterDataMapper.query(meterData);
        if (meterDatas.isEmpty()) {
            //插入仪表返回数据
            int i = meterDataMapper.insert(meterData);

            //更新仪表的最后一次抄表数据
            Meter meterForUpdate = new Meter();
            meterForUpdate.setMeterCode(msg.getOriginator());
            meterForUpdate.setLastUpLoadTime(meterData.getSmsDate());
            meterForUpdate.setLastValveStatus(meterData.getValveStatus());
            meterForUpdate.setLastValue(meterData.getValue());
            meterMapper.updateLastByCode(meterForUpdate);

            //更新用户已使用的仪表记录
            UserInfo userInfoForSearch = new UserInfo();
            userInfoForSearch.setMeterId(meter.getId());
            UserInfo userInfo = userInfoMapper.get(userInfoForSearch);
            if (userInfo != null){
                //计算用户累计使用水量
                Float lastValue = meter.getLastValue() == null ? 0f : meter.getLastValue();
                Float basiceValue = meter.getBasicValue() == null ? 0f : meter.getBasicValue();
                Float currentData = meterData.getValue() == null ? 0f : meterData.getValue();

                Float currentUseValue = userInfo.getCurrentUseValue() == null ? 0f : userInfo.getCurrentUseValue();
                if (lastValue <= 0 || currentData - lastValue <= 0 ){
                    currentUseValue += currentData - basiceValue;
                }else {
                    currentUseValue +=  currentData - lastValue;
                }

                UserInfo userInfoForUpdate = new UserInfo();
                userInfoForUpdate.setId(userInfo.getId());
                userInfoForUpdate.setCurrentUseValue(currentUseValue);
                userInfoMapper.updateByPrimaryKey(userInfoForUpdate);
            }
            if (i > 0) {
                org.smslib.Service.getInstance().deleteMessage(msg);
            }
        } else {
            org.smslib.Service.getInstance().deleteMessage(msg);
        }
    }
}