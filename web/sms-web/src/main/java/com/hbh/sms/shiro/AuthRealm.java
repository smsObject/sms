package com.hbh.sms.shiro;

import com.hbh.sms.dal.dao.SystemUserMapper;
import com.hbh.sms.model.entity.SystemMenu;
import com.hbh.sms.model.entity.SystemUser;
import com.sms.common.SystemConst;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hbh on 2017/8/30.
 */
public class AuthRealm extends AuthorizingRealm {

    private static final String NAME = "安全认证:";
    @Autowired
    private SystemUserMapper systemUserMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) getAvailablePrincipal(principalCollection);
        HashMap<String, Object> sqlParams = new HashMap<>();
        SystemUser search = new SystemUser();
        search.setName(username);
        SystemUser user = systemUserMapper.findOne(search);
        if (user != null) {
            //权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
//            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//            List<SystemMenu> menuList = null;
//            List<Integer> menuIdList = roleDao.findAllRoleMenuIdList(user.getRoleId());
//            if (ListUtils.isNotEmpty(menuIdList)) {
//                sqlParams.clear();
//                sqlParams.put("qMenuIds", ListUtils.join(menuIdList, BaseConst.SEPARATOR));
//                menuList = menuDao.findAll(sqlParams);
//            }
//            if (menuList != null) {
//                for (Menu menu : menuList) {
//                    simpleAuthorizationInfo.addStringPermission(menu.getPermissionString());
//                }
//            }
//            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        String logHead = NAME + "[" + username + "]:";
//        logger.info(logHead + "请求登录!");
        SystemUser systemUser = new SystemUser();
        systemUser.setName(username);
        SystemUser user = systemUserMapper.findOne(systemUser);
        if (user == null) {
            throw new UnknownAccountException("找不到用户");
        }
//        if (user.getStatus() != SystemConst.USER_STATUS_ENABLED) {
//            throw new LockedAccountException("账户已被禁用");
//        }
        return new SimpleAccount(username, user.getPassword(), getName());
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, String permission) {
        if (SecurityUtils.getSubject().getPrincipal().toString().equals(SystemConst.USER_NAME_ADMIN)) {
//            logger.info(NAME + "内置admin用户直接通过!");
            return true;
        }
        return super.isPermitted(principals, permission);
    }
}
