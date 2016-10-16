package com.hbh.sms.biz.service.MeterData.impl;

import com.hbh.sms.dal.dao.MeterDataMapper;
import com.hbh.sms.biz.service.MeterData.MeterDataService;
import com.hbh.sms.model.entity.MeterData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hbh on 2016/8/18.
 */
@Service
public class MeterDataServiceImpl implements MeterDataService {

    @Autowired
    private MeterDataMapper meterDataMapper;

    public int insert(MeterData record) {
        if (record == null )return  -1;
        try {
            meterDataMapper.insert(record);
        }catch (Exception e){
            return -1;
        }
        return 1;
    }

    public Result<List<MeterData>> list(MeterData record) {
        if (record == null) return  ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        try {
            int count=meterDataMapper.count(record);
            List<MeterData> result=meterDataMapper.list(record);
            return ResultUtil.newSuccessResult(result , count);
        }catch (Exception e){
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }
}