package com.hbh.sms.service.meter;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.Meter;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
public interface MeterService {
    public void add(Meter meter);
    public List<Meter> list(Meter meter);
    public void update(Meter meter);
    public void  delete(Integer id);
    public List<Meter> page(Meter meter);
}
