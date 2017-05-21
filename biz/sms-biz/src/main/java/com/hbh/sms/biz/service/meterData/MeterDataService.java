package com.hbh.sms.biz.service.meterData;

import com.hbh.sms.model.entity.MeterData;
import com.sms.common.PagedData;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/8/18.
 */
public interface MeterDataService {
      Result<Long> insert(MeterData meterData);
      Result<PagedData<MeterData>> page(MeterData meterData);
      Result<List<MeterData>> list(MeterData meterData);
}