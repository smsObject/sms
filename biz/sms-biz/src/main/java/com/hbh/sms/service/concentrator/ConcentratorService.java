package com.hbh.sms.service.concentrator;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.Meter;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
public interface ConcentratorService {
    public void add(Concentrator concentrator);
    public Result<List<Concentrator>> list(Concentrator concentrator);
    public Result<Concentrator> getConcentratorById(Integer id);
    public void update(Concentrator concentrator);
    public void  delete(Integer id);
    public Result<List<Concentrator>> page(Concentrator concentrator);
}
