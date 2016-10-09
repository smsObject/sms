package com.hbh.sms.biz.service.meter;

import com.hbh.sms.biz.model.entity.Meter;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
public interface MeterService {
    public void add(Meter meter);
    public Result<List<Meter>> list(Meter meter);
    public Result<Boolean> update(Meter meter);
    public void  delete(Integer id);
    public Result<Meter> getMeterById(Integer id);
    public Result<List<Meter>> page(Meter meter);
}
