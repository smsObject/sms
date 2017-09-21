package com.hbh.sms.controllers;

import com.hbh.sms.biz.service.system.SystemUserService;
import com.hbh.sms.dal.dao.SystemMenuMapper;
import com.hbh.sms.model.entity.SystemMenu;
import com.hbh.sms.model.entity.SystemUser;
import com.hbh.sms.shiro.UserUtil;
import com.sms.common.MD5Util;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录、登出
 * <p/>
 * Created by hbh
 * on 16/5/23.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private SystemUserService systemUserService;
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    @RequestMapping("/index")
    public String index() {
        return login();
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signIn")
    @ResponseBody
    public Result<Boolean> signIn(String userName, String password, RedirectAttributes redirectAttributes) {
        UsernamePasswordToken authToken = new UsernamePasswordToken(userName, MD5Util.getMD5(password), false);
        try {
            //使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
            SecurityUtils.getSubject().login(authToken);
            //记录登录信息
            SystemUser user = systemUserService.findSystemUserByUserName(userName);
//            User userForUpdate = new User();
//            userForUpdate.setUserId(user.getUserId());
//            userForUpdate.setLastLoginIp(WebUtils.getClientIp(getRequest()));
//            userForUpdate.setLastLoginTime(DateUtils.getCurrentSecondIntValue());
//            if (!userService.updateSelective(userForUpdate)) {
//                throw new RuntimeException("登录信息保存失败!");
//            }
            return ResultUtil.newSuccessResult(true);
        } catch (AuthenticationException e) {
            logger.info(userName + "登录失败!");
            redirectAttributes.addFlashAttribute("flash_message", "用户名或密码错误!");
            redirectAttributes.addFlashAttribute("userName", userName);
            return ResultUtil.newFailedResult(StateCode.ERROR,"用户名或密码错误");
        }
    }

    @RequestMapping("/getUserPermission")
    @ResponseBody
    public Result<Map<String,Integer>> getUserPermission(){
        SystemUser systemUser = UserUtil.getCurrentUser();
        Long roleId = systemUser.getRoleId();
        List<SystemMenu> systemMenus = systemMenuMapper.getSystemMenuByRoleId(roleId);
        Map<String,Integer> systemMenuMap = new HashMap<>();
        for (SystemMenu systemMenu : systemMenus){
            systemMenuMap.put(systemMenu.getCode(),1);
        }
        return ResultUtil.newSuccessResult(systemMenuMap);
    }

    @RequestMapping("/signOut")
    @ResponseBody
    public Result<String> signOut(HttpServletRequest request, HttpServletResponse response) {

        // Log the user out of the application.
        SecurityUtils.getSubject().logout();
        //清空cookie信息
        //AuthUtil.clearCookie(request, response);

        // For now, redirect back to the home page.
        return ResultUtil.newSuccessResult("");
    }
}
