package com.hbh.sms.biz.service;

import com.hbh.sms.biz.model.entity.Concentrator;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/7/31.
 */
public interface DeviceService {

    public Result<List<Concentrator>> scanner();


}
