package com.hbh.sms.biz.service.system.impl;

import com.hbh.sms.biz.service.system.SystemUserService;
import com.hbh.sms.model.entity.SystemUser;
import com.sms.common.PagedData;
import com.sms.common.Result;
import org.springframework.stereotype.Service;

/**
 * Created by hbh
 */
@Service("systemUserService")
public class SystemUserServiceImpl implements SystemUserService {
    @Override
    public Result<Long> add(SystemUser systemUser) {
        return null;
    }

    @Override
    public Result<Boolean> deleteById(Long id) {
        return null;
    }

    @Override
    public Result<Boolean> update(SystemUser systemUser) {
        return null;
    }

    @Override
    public Result<PagedData<SystemUser>> page(SystemUser systemUser) {
        return null;
    }

    @Override
    public Result<SystemUser> getUserInfoById(Long id) {
        return null;
    }
}