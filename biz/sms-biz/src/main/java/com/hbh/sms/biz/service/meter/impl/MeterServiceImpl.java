package com.hbh.sms.biz.service.meter.impl;

import com.hbh.sms.biz.dal.dao.MeterMapper;
import com.hbh.sms.biz.service.meter.MeterService;
import com.hbh.sms.biz.model.entity.Meter;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
@Service("meterService")
public class MeterServiceImpl implements MeterService {

    @Autowired
    private MeterMapper meterMapper;

    public void add(Meter meter) {
        if (meter == null) return;
        try {
            meterMapper.insert(meter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Result<List<Meter>> list(Meter meter) {
        if (meter == null) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        try {
            int count = meterMapper.count(meter);
            List<Meter> meters = meterMapper.list(meter);
            return ResultUtil.newSuccessResult(meters, count);
        } catch (Exception e) {
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    public Result<Boolean> update(Meter meter) {
        if (meter == null) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数缺失");
        try {
            int result = meterMapper.updateByPrimaryKey(meter);
            return ResultUtil.newSuccessResult(result > 0);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    public void delete(Integer id) {
        if (id == null || id == 0) return;
        try {
            meterMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Result<Meter> getMeterById(Integer id) {
        if (id == null || id == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            Meter meter = meterMapper.selectByPrimaryKey(id);
            return ResultUtil.newSuccessResult(meter);
        } catch (Exception e) {
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    public Result<List<Meter>> page(Meter meter) {
        if (meter == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            int size = meterMapper.count(meter);
            List<Meter> meters = meterMapper.list(meter);
            return ResultUtil.newSuccessResult(meters, size);
        } catch (Exception e) {
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }
}