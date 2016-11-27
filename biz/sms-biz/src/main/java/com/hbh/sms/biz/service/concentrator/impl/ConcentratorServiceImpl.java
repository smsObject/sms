package com.hbh.sms.biz.service.concentrator.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.dal.dao.ConcentratorMapper;
import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.biz.service.concentrator.ConcentratorService;
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
@Service("concentratorService")
public class ConcentratorServiceImpl implements ConcentratorService {

    @Autowired
    private ConcentratorMapper concentratorMapper;

    public Result<Long> add(Concentrator concentrator) {
        if (concentrator == null) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数不能为空");
        concentratorMapper.insert(concentrator);
        return ResultUtil.newSuccessResult(concentrator.getId());
    }

    public Result<List<Concentrator>> list(Concentrator concentrator) {
        if (concentrator == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        List<Concentrator> concentrators = concentratorMapper.query(concentrator);
        return ResultUtil.newSuccessResult(concentrators);
    }

    public Result<Concentrator> getConcentratorById(Long id) {
        if (id == null || id.longValue() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Concentrator concentrator = concentratorMapper.selectByPrimaryKey(id);
        return ResultUtil.newSuccessResult(concentrator);
    }

    public Result<Boolean> update(Concentrator concentrator) {
        if (concentrator == null) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数不能为空");
        int i = concentratorMapper.updateByPrimaryKey(concentrator);
        return ResultUtil.newSuccessResult(i > 0);
    }

    public Result<Boolean> delete(Long id) {
        if (id == null || id == 0) return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数不能为空");
        int i = concentratorMapper.deleteByPrimaryKey(id);
        return ResultUtil.newSuccessResult(i > 0);
    }

    public Result<PagedData<Concentrator>> page(Concentrator concentrator) {
        if (concentrator == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<PagedData<Concentrator>> result = null;
        Page page = PageHelper.startPage(concentrator.getPageNo(), concentrator.getPageSize());
        List<Concentrator> list = concentratorMapper.query(concentrator);
        PagedData<Concentrator> pagedData = new PagedData<>();
        pagedData.setPageNo(concentrator.getPageNo());
        pagedData.setPageSize(concentrator.getPageSize());
        pagedData.setTotalSize(page.getTotal());
        pagedData.setResultList(list);
        result = ResultUtil.newSuccessResult(pagedData);
        return result;
    }

    public Result<Boolean> updateByComPort(Concentrator concentrator) {
        if (concentrator == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数不能为空");
        }
        int i = concentratorMapper.updateByComPort(concentrator);
        return ResultUtil.newSuccessResult(i > 0);
    }

}