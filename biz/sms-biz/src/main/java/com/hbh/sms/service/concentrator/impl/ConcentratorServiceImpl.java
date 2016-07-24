package com.hbh.sms.service.concentrator.impl;

import com.hbh.sms.dal.dao.ConcentratorMapper;
import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.service.concentrator.ConcentratorService;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
@Service("concentratorService")
public class ConcentratorServiceImpl implements ConcentratorService {

    @Autowired
    private ConcentratorMapper concentratorMapper;

    public void add(Concentrator concentrator) {

    }

    public List<Concentrator> list(Concentrator concentrator) {
        return null;
    }

    public Result<Concentrator> getConcentratorById(Integer id) {
        if (id == null || id == 0){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            Concentrator concentrator =  concentratorMapper.selectByPrimaryKey(id);
            return ResultUtil.newSuccessResult(concentrator);
        }catch (Exception e){
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    public void update(Concentrator concentrator) {

    }

    public void delete(Integer id) {

    }

    public Result<List<Concentrator>> page(Concentrator concentrator) {
        if (concentrator == null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            int size= concentratorMapper.count(concentrator);
            List<Concentrator> concentrators=concentratorMapper.list(concentrator);
            return ResultUtil.newSuccessResult(concentrators,size);
        }catch (Exception e){
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }
}
