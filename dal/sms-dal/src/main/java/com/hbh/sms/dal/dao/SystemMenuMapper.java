package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.SystemMenu;

import java.util.List;

/**
 * @author hbh
 **/
public interface SystemMenuMapper {
    List<SystemMenu> getSystemMenuList();
    List<SystemMenu> getSystemMenuByRoleIds(List<Long> ids);
}