package com.hbh.sms.biz.service.system;

import com.hbh.sms.model.entity.SystemMenu;
import com.sms.common.Result;

import java.util.List;

/**
 * @author hbh
 **/
public interface SystemMenuService {
    Result<List<SystemMenu>> getSystemMenuList();
    Result<List<SystemMenu>> getSystemMenuByRoleIds(List<Long> ids);
}