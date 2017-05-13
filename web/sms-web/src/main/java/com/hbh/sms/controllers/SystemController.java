package com.hbh.sms.controllers;

import com.hbh.sms.biz.service.system.SystemRoleService;
import com.hbh.sms.biz.service.system.SystemUserService;
import com.hbh.sms.model.entity.SystemRole;
import com.hbh.sms.model.entity.SystemUser;
import com.sms.common.PagedData;
import com.sms.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hbh
 */
@RequestMapping("/system")
@Controller
public class SystemController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemRoleService systemRoleService;

    @RequestMapping("/pageUser")
    public Result<PagedData<SystemUser>> pageUser(SystemUser systemUser){
        return systemUserService.page(systemUser);
    }

    @RequestMapping("/pageRole")
    public Result<PagedData<SystemRole>> pageRole(SystemRole systemRole){
        return systemRoleService.page(systemRole);
    }

    @RequestMapping("/addUser")
    public Result<Long> addUser(SystemUser systemUser){
        return systemUserService.add(systemUser);
    }

    @RequestMapping("/addRole")
    public Result<Long> addRole(SystemRole systemRole){
        return systemRoleService.add(systemRole);
    }

    @RequestMapping("/updateUser")
    public Result<Boolean> updateUser(SystemUser systemUser){
        return systemUserService.update(systemUser);
    }

    @RequestMapping("/updateRole")
    public Result<Boolean> updateRole(SystemRole systemRole){
        return systemRoleService.update(systemRole);
    }

    @RequestMapping("/deleteUser")
    public Result<Boolean> deleteUser(Long id){
        return systemUserService.deleteById(id);
    }

    @RequestMapping("/deleteRole")
    public Result<Boolean> deleteRole(Long id){
        return systemRoleService.deleteById(id);
    }

}