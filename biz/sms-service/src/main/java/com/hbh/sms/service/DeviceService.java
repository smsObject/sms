package com.hbh.sms.service;

import com.hbh.sms.model.entity.Concentrator;
import com.sms.common.Result;

/**
 * Created by hbh on 2016/7/31.
 */
public interface DeviceService {

    public Result<Concentrator> scanner();
}
