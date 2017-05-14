package com.hbh.sms.biz.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.biz.service.system.SystemUserService;
import com.hbh.sms.dal.dao.SystemRoleMapper;
import com.hbh.sms.dal.dao.SystemUserMapper;
import com.hbh.sms.model.entity.SystemRole;
import com.hbh.sms.model.entity.SystemUser;
import com.hbh.sms.model.entity.SystemUserRole;
import com.sms.common.PagedData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbh
 */
@Service("systemUserService")
public class SystemUserServiceImpl implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;
    @Resource
    private SystemRoleMapper systemRoleMapper;

    @Override
    public Result<Long> add(SystemUser systemUser) {
        List<Long> roleIds = systemUser.getRoleIds();
        if (roleIds == null || roleIds.isEmpty()) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "岗位必填");
        }
        systemUserMapper.insert(systemUser);
        Long userId = systemUser.getId();
        String createPerson = systemUser.getOperator();
        if (roleIds != null && !roleIds.isEmpty()) {
            List<SystemUserRole> systemUserRoles = new ArrayList<>();
            for (Long roleId : roleIds) {
                SystemUserRole systemUserRole = new SystemUserRole();
                systemUserRole.setRoleId(roleId);
                systemUserRole.setUserId(userId);
                systemUserRole.setCreatePerson(createPerson);
                systemUserRoles.add(systemUserRole);
            }
            systemRoleMapper.batchInsertUserRole(systemUserRoles);
        }
        return ResultUtil.newSuccessResult(systemUser.getId());
    }

    @Override
    public Result<Boolean> deleteById(Long id) {
        int i = systemUserMapper.deleteByPrimaryKey(id);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Override
    public Result<Boolean> update(SystemUser systemUser) {
        List<Long> roleIds = systemUser.getRoleIds();
        Long userId = systemUser.getId();
        String updatePerson = systemUser.getOperator();
        if (roleIds == null || roleIds.isEmpty()) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "岗位必填");
        }
        int i = systemUserMapper.updateByPrimaryKey(systemUser);
        systemRoleMapper.deleteUserRoleByUserId(userId);
        List<SystemUserRole> systemUserRoles = new ArrayList<>();
        for (Long roleId : roleIds) {
            SystemUserRole systemUserRole = new SystemUserRole();
            systemUserRole.setCreatePerson(updatePerson);
            systemUserRole.setUserId(userId);
            systemUserRole.setRoleId(roleId);
            systemUserRoles.add(systemUserRole);
        }
        systemRoleMapper.batchInsertUserRole(systemUserRoles);
        return ResultUtil.newSuccessResult(i > 0);
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
}