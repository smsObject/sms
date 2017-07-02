package com.hbh.sms.controllers;

import com.hbh.sms.biz.service.system.SystemMenuService;
import com.hbh.sms.biz.service.system.SystemRoleService;
import com.hbh.sms.biz.service.system.SystemUserService;
import com.hbh.sms.model.entity.SystemMenu;
import com.hbh.sms.model.entity.SystemRole;
import com.hbh.sms.model.entity.SystemUser;
import com.sms.common.PagedData;
import com.sms.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by hbh
 */
@RequestMapping("/sms/system")
@Controller
public class SystemController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemMenuService systemMenuService;

    @RequestMapping("/pageUser")
    @ResponseBody
    public Result<PagedData<SystemUser>> pageUser(SystemUser systemUser){
        return systemUserService.page(systemUser);
    }

    @RequestMapping("/pageRole")
    @ResponseBody
    public Result<PagedData<SystemRole>> pageRole(SystemRole systemRole){
        return systemRoleService.page(systemRole);
    }

    @RequestMapping("/roleList")
    @ResponseBody
    public Result<List<SystemRole>> roleList(SystemRole systemRole){
        return systemRoleService.list(systemRole);
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Result<Long> addUser(SystemUser systemUser){
        return systemUserService.add(systemUser);
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public Result<Long> addRole(SystemRole systemRole, HttpServletRequest request){
        return systemRoleService.add(systemRole);
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Result<Boolean> updateUser(SystemUser systemUser){
        return systemUserService.update(systemUser);
    }

    @RequestMapping("/updateRole")
    @ResponseBody
    public Result<Boolean> updateRole(SystemRole systemRole){
        return systemRoleService.update(systemRole);
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Result<Boolean> deleteUser(Long id){
        return systemUserService.deleteById(id);
    }

    @RequestMapping("/deleteRole")
    @ResponseBody
    public Result<Boolean> deleteRole(Long id){
        return systemRoleService.deleteById(id);
    }

    @RequestMapping("/queryMenu")
    @ResponseBody
    public Result<List<SystemMenu>> queryMenu(){
        return systemMenuService.getSystemMenuList();
    }
}