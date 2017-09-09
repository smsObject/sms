package com.hbh.sms.shiro;

import com.hbh.sms.biz.service.system.SystemUserService;
import com.hbh.sms.model.entity.SystemUser;
import com.sms.common.SpringUtil;
import org.apache.shiro.SecurityUtils;

/**
 * Created by hubihua on 2017/8/30.
 */
public class UserUtil {

    private static SystemUserService systemUserService = SpringUtil.getBean("systemUserService", SystemUserService.class);

    public static SystemUser getCurrentUser() {
        return systemUserService.findSystemUserByUserName(SecurityUtils.getSubject().getPrincipal().toString());
    }

    public static Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

    public static SystemUser getUserById(Long userId){
        return systemUserService.getUserInfoById(userId).getData();
    }

    public static String getUserNameById(Long userId){
        SystemUser user = getUserById(userId);
        if (user == null){
            return "";
        }
        return user.getName();
    }
}