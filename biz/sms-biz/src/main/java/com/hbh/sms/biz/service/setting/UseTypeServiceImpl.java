package com.hbh.sms.biz.service.setting;

import com.hbh.sms.dal.dao.UseTypeMapper;
import com.hbh.sms.model.entity.UseType;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hbh on 2017/7/9.
 */
@Service("useTypeService")
public class UseTypeServiceImpl implements UseTypeService {
    @Resource
    private UseTypeMapper useTypeMapper;
    @Override
    public Result<List<UseType>> listUseType(UseType useType) {
        List<UseType>  list = useTypeMapper.query(useType);
        return ResultUtil.newSuccessResult(list);
    }

    @Override
    public Result<Long> addUseType(UseType useType) {
        useType.setCreatePerson("system");
        useTypeMapper.insert(useType);
        return ResultUtil.newSuccessResult(useType.getId());
    }

    @Override
    public Result<Boolean> updateUseType(UseType useType) {
        int i = useTypeMapper.update(useType);
        return ResultUtil.newSuccessResult(i>0);
    }

    @Override
    public Result<Boolean> deleteUseType(Long id) {
        int i = useTypeMapper.delete(id);
        return ResultUtil.newSuccessResult(i>0);
    }
}