package com.hbh.sms.biz.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.biz.service.system.SystemUserService;
import com.hbh.sms.dal.dao.SystemRoleMapper;
import com.hbh.sms.dal.dao.SystemUserMapper;
import com.hbh.sms.model.entity.SystemUser;
import com.sms.common.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hbh
 */
@Service("systemUserService")
public class SystemUserServiceImpl implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public Result<Long> add(SystemUser systemUser) {
        if (systemUser == null || systemUser.getRoleId() == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "岗位必填");
        }
        //判断手机不能重复
        SystemUser search = new SystemUser();
        search.setName(systemUser.getName());
        List<SystemUser> list = systemUserMapper.query(search);
        if (list.size() > 0) {
            return ResultUtil.newFailedResult(StateCode.ERROR, "用户名已存在");
        }
        systemUser.setPassword(MD5Util.getMD5(systemUser.getName()));
        systemUserMapper.insert(systemUser);
        return ResultUtil.newSuccessResult(systemUser.getId());
    }

    @Override
    public Result<Boolean> deleteById(Long id) {
        int i = systemUserMapper.deleteByPrimaryKey(id);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Override
    public Result<Boolean> update(SystemUser systemUser) {
        if (systemUser == null || systemUser.getRoleId() == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "岗位必填");
        }

        //判断手机不能重复
        SystemUser search = new SystemUser();
        search.setName(systemUser.getName());
        List<SystemUser> list = systemUserMapper.query(search);
        if (list.size() > 0) {
            SystemUser systemUser1 = list.get(0);
            if (systemUser.getId().longValue() != systemUser1.getId().longValue()) {
                return ResultUtil.newFailedResult(StateCode.ERROR, "用户名称已存在");
            }
        }
        return ResultUtil.newSuccessResult(systemUserMapper.updateByPrimaryKey(systemUser) > 0);
    }

    @Override
    public Result<PagedData<SystemUser>> page(SystemUser systemUser) {
        Result<PagedData<SystemUser>> result = null;
        Page page = PageHelper.startPage(systemUser.getPageNo(), systemUser.getPageSize());
        List<SystemUser> list = systemUserMapper.query(systemUser);
        PagedData<SystemUser> pagedData = new PagedData<>();
        pagedData.setPageNo(systemUser.getPageNo());
        pagedData.setPageSize(systemUser.getPageSize());
        pagedData.setTotalSize(page.getTotal());
        pagedData.setResultList(list);
        return ResultUtil.newSuccessResult(pagedData);
    }

    @Override
    public Result<SystemUser> getUserInfoById(Long id) {
        SystemUser systemUser = new SystemUser();
        systemUser.setId(id);
        List<SystemUser> list = systemUserMapper.query(systemUser);
        systemUser = list.isEmpty() ? null : list.get(0);
        return ResultUtil.newSuccessResult(systemUser);
    }

    @Override
    public SystemUser findSystemUserByUserName(String userName) {
        SystemUser search = new SystemUser();
        search.setName(userName);
        return systemUserMapper.findOne(search);
    }
}