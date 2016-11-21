package com.hbh.sms.biz.service.MeterData;

import com.hbh.sms.model.entity.MeterData;
import com.sms.common.PagedData;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/8/18.
 */
public interface MeterDataService {
    public Result<Long> insert(MeterData record);
    public Result<PagedData<MeterData>> page(MeterData record);
}