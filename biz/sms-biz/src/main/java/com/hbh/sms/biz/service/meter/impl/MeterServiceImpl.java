package com.hbh.sms.biz.service.meter.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.dal.dao.MeterMapper;
import com.hbh.sms.biz.service.meter.MeterService;
import com.hbh.sms.model.entity.Meter;
import com.sms.common.PagedData;
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

    public Result<Long> add(Meter meter) {
        if (meter == null) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        meterMapper.insert(meter);
        return ResultUtil.newSuccessResult(meter.getId());
    }

    public Result<List<Meter>> list(Meter meter) {
        if (meter == null) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        return null;
    }

    public Result<Boolean> update(Meter meter) {
        if (meter == null) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数缺失");
        int result = meterMapper.updateByPrimaryKey(meter);
        return ResultUtil.newSuccessResult(result > 0);
    }

    public Result<Boolean> delete(Long id) {
        if (id == null || id.longValue() == 0) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        int i = meterMapper.deleteByPrimaryKey(id);
        return ResultUtil.newSuccessResult(i > 0);
    }

    public Result<Meter> getMeterById(Long id) {
        if (id == null || id.longValue() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Meter meter = meterMapper.selectByPrimaryKey(id);
        return ResultUtil.newSuccessResult(meter);
    }

    public Result<PagedData<Meter>> page(Meter meter) {
        if (meter == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<PagedData<Meter>> result = null;
        Page page = PageHelper.startPage(meter.getPageNo() , meter.getPageSize());
        List<Meter> list = meterMapper.query(meter);
        PagedData<Meter> pagedData = new PagedData<>();
        pagedData.setPageNo(meter.getPageNo());
        pagedData.setPageSize(meter.getPageSize());
        pagedData.setTotalSize(page.getTotal());
        pagedData.setResultList(list);
        result = ResultUtil.newSuccessResult(pagedData);
        return result;
    }

    @Override
    public Result<List<Meter>> queryNoCorrelationMeters(Long userId) {
        List<Meter> meters = meterMapper.queryNoCorrelationMeters(userId);
        return ResultUtil.newSuccessResult(meters);
    }
}