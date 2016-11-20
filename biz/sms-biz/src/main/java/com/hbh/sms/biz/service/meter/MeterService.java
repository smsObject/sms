package com.hbh.sms.biz.service.meter;

import com.hbh.sms.model.entity.Meter;
import com.sms.common.PagedData;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
public interface MeterService {
    public Result<Long> add(Meter meter);
    public Result<List<Meter>> list(Meter meter);
    public Result<Boolean> update(Meter meter);
    public Result<Boolean>  delete(Long id);
    public Result<Meter> getMeterById(Long id);
    public Result<PagedData<Meter>> page(Meter meter);
}
