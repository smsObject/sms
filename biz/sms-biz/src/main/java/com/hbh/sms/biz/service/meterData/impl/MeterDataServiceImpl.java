package com.hbh.sms.biz.service.meterData.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.dal.dao.MeterDataMapper;
import com.hbh.sms.biz.service.meterData.MeterDataService;
import com.hbh.sms.model.entity.MeterData;
import com.sms.common.PagedData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Created by hbh on 2016/8/18.
 */
@Service
public class MeterDataServiceImpl implements MeterDataService {

    @Autowired
    private MeterDataMapper meterDataMapper;

    public Result<Long> insert(MeterData record) {
        if (record == null) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        meterDataMapper.insert(record);
        return ResultUtil.newSuccessResult(record.getId());
    }

    public Result<PagedData<MeterData>> page(MeterData record) {
        if (record == null) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        Result<PagedData<MeterData>> result = null;
        Page page = PageHelper.startPage(record.getPageNo(),record.getPageSize());
        List<MeterData> list = meterDataMapper.query(record);
        PagedData<MeterData> pagedData = new PagedData<>();
        pagedData.setPageNo(record.getPageNo());
        pagedData.setPageSize(record.getPageSize());
        pagedData.setTotalSize(page.getTotal());
        pagedData.setResultList(list);
        result = ResultUtil.newSuccessResult(pagedData);
        return result;
    }

    @Override
    public Result<List<MeterData>> list(MeterData meterData) {
        return null;
    }
}