package com.hbh.sms.biz.service.system;

import com.hbh.sms.model.entity.SystemUser;
import com.sms.common.PagedData;
import com.sms.common.Result;

/**
 * Created by hbh
 */
public interface SystemUserService {
    Result<Long> add(SystemUser systemUser);
    Result<Boolean> deleteById(Long id);
    Result<Boolean> update(SystemUser systemUser);
    Result<PagedData<SystemUser>> page(SystemUser systemUser);
    Result<SystemUser> getUserInfoById(Long id);
    SystemUser findSystemUserByUserName(String userName);
}