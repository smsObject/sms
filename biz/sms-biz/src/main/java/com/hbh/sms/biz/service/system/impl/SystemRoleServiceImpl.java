package com.hbh.sms.biz.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.biz.service.system.SystemRoleService;
import com.hbh.sms.dal.dao.SystemRoleMapper;
import com.hbh.sms.model.entity.SystemRole;
import com.sms.common.PagedData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hbh
 */
@Service("systemRoleService")
public class SystemRoleServiceImpl implements SystemRoleService {
    @Resource
    private SystemRoleMapper systemRoleMapper;

    @Override
    public Result<Long> add(SystemRole systemRole) {
        systemRoleMapper.insert(systemRole);
        return ResultUtil.newSuccessResult(systemRole.getId());
    }

    @Override
    public Result<Boolean> deleteById(Long id) {
        int i = systemRoleMapper.deleteByPrimaryKey(id);
        return ResultUtil.newSuccessResult(i>0);
    }

    @Override
    public Result<Boolean> update(SystemRole systemRole) {
        int i = systemRoleMapper.updateByPrimaryKey(systemRole);
        return ResultUtil.newSuccessResult(i>0);
    }

    @Override
    public Result<PagedData<SystemRole>> page(SystemRole systemRole) {
        Result<PagedData<SystemRole>> result = null;
        Page page = PageHelper.startPage(systemRole.getPageNo() , systemRole.getPageSize());
        List<SystemRole> list = systemRoleMapper.query(systemRole);
        PagedData<SystemRole> pagedData = new PagedData<>();
        pagedData.setPageNo(systemRole.getPageNo());
        pagedData.setPageSize(systemRole.getPageSize());
        pagedData.setResultList(list);
        pagedData.setTotalSize(page.getTotal());
        return ResultUtil.newSuccessResult(pagedData);
    }

    @Override
    public Result<SystemRole> getUserInfoById(Long id) {
        SystemRole systemRole = new SystemRole();
        systemRole.setId(id);
        List<SystemRole> list = systemRoleMapper.query(systemRole);
        systemRole = list.isEmpty()?null:list.get(0);
        return ResultUtil.newSuccessResult(systemRole);
    }
}
