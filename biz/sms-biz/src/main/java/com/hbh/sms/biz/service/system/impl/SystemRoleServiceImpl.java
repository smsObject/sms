package com.hbh.sms.biz.service.system.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.biz.service.system.SystemRoleService;
import com.hbh.sms.dal.dao.SystemRoleMapper;
import com.hbh.sms.dal.dao.SystemUserMapper;
import com.hbh.sms.model.entity.SystemRole;
import com.hbh.sms.model.entity.SystemRoleMenu;
import com.hbh.sms.model.entity.SystemUser;
import com.hbh.sms.model.entity.SystemUserRole;
import com.sms.common.PagedData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbh
 */
@Service("systemRoleService")
public class SystemRoleServiceImpl implements SystemRoleService {
    @Resource
    private SystemRoleMapper systemRoleMapper;
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    @Transactional
    public Result<Long> add(SystemRole systemRole) {
        String str = systemRole.getMenuIds();
        if (str == null || str.trim().length() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        String[] menuIds = str.split(",");
        SystemRole search = new SystemRole();
        search.setName(systemRole.getName());
        List<SystemRole> list = systemRoleMapper.query(search);
        if (list.size() > 0){
            return ResultUtil.newFailedResult(StateCode.ERROR,"角色已存在无需再建");
        }

        systemRoleMapper.insert(systemRole);
        Long roleId = systemRole.getId();
        String createPerson = systemRole.getOperator();
        List<SystemRoleMenu> systemRoleMenus = new ArrayList<>();
        for (String menuId : menuIds) {
            SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
            systemRoleMenu.setRoleId(roleId);
            systemRoleMenu.setMenuId(Long.valueOf(menuId));
            systemRoleMenu.setCreatePerson(createPerson);
            systemRoleMenus.add(systemRoleMenu);
        }
        systemRoleMapper.batchInsertRoleMenu(systemRoleMenus);
        return ResultUtil.newSuccessResult(systemRole.getId());
    }

    @Override
    public Result<Boolean> deleteById(Long id) {
        //查询该岗位下是否有用户
        Result<SystemRole> result = getRoleById(id);
        SystemRole systemRole = result.getData();
        if (systemRole == null){
            return ResultUtil.newFailedResult(StateCode.ERROR,"该角色不存在");
        }
        SystemUser search = new SystemUser();
        search.setRoleId(id);
        SystemUser systemUserMapperOne = systemUserMapper.findOne(search);

        if (systemUserMapperOne != null){
            return ResultUtil.newFailedResult(StateCode.ERROR,"该角色下有用户不能删除");
        }

        int i = systemRoleMapper.deleteByPrimaryKey(id);
        systemRoleMapper.deleteRoleMenuByRoleId(id);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Override
    public Result<Boolean> update(SystemRole systemRole) {
        String str = systemRole.getMenuIds();
        if (str == null || str.trim().length() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }

        SystemRole search = new SystemRole();
        search.setName(systemRole.getName());
        List<SystemRole> list = systemRoleMapper.query(search);
        if (list.size() > 0){
            if(list.get(0).getId().longValue() != systemRole.getId().longValue()){
                return ResultUtil.newFailedResult(StateCode.ERROR,"角色已存在");
            }
        }

        String[] menuIds = str.split(",");
        Long roleId = systemRole.getId();
        String createPerson = systemRole.getOperator();
        List<SystemRoleMenu> systemRoleMenus = new ArrayList<>();
        for (String menuId : menuIds) {
            SystemRoleMenu systemRoleMenu = new SystemRoleMenu();
            systemRoleMenu.setRoleId(roleId);
            systemRoleMenu.setMenuId(Long.valueOf(menuId));
            systemRoleMenu.setCreatePerson(createPerson);
            systemRoleMenus.add(systemRoleMenu);
        }
        systemRoleMapper.deleteRoleMenuByRoleId(roleId);
        systemRoleMapper.batchInsertRoleMenu(systemRoleMenus);
        int i = systemRoleMapper.updateByPrimaryKey(systemRole);
        return ResultUtil.newSuccessResult(i > 0);
    }

    @Override
    public Result<PagedData<SystemRole>> page(SystemRole systemRole) {
        Page page = PageHelper.startPage(systemRole.getPageNo(), systemRole.getPageSize());
        List<SystemRole> list = systemRoleMapper.query(systemRole);
        PagedData<SystemRole> pagedData = new PagedData<>();
        pagedData.setPageNo(systemRole.getPageNo());
        pagedData.setPageSize(systemRole.getPageSize());
        pagedData.setResultList(list);
        pagedData.setTotalSize(page.getTotal());
        return ResultUtil.newSuccessResult(pagedData);
    }

    @Override
    public Result<SystemRole> getRoleById(Long id) {
        SystemRole systemRole = new SystemRole();
        systemRole.setId(id);
        List<SystemRole> list = systemRoleMapper.query(systemRole);
        systemRole = list.isEmpty() ? null : list.get(0);
        return ResultUtil.newSuccessResult(systemRole);
    }

    @Override
    public Result<List<SystemRole>> list(SystemRole systemRole) {
        List<SystemRole> list = systemRoleMapper.query(systemRole);
        return ResultUtil.newSuccessResult(list);
    }
}