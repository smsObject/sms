package com.hbh.sms.biz.service.MeterData;

import com.hbh.sms.model.entity.MeterData;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/8/18.
 */
public interface MeterDataService {
    public int insert(MeterData record);
    public Result<List<MeterData>> list(MeterData record);
}