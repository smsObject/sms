package com.hbh.sms.biz.service.system.impl;

import com.hbh.sms.biz.service.system.SystemRoleService;
import com.hbh.sms.model.entity.SystemRole;
import com.sms.common.PagedData;
import com.sms.common.Result;
import org.springframework.stereotype.Service;

/**
 * Created by hbh
 */
@Service("systemRoleService")
public class SystemRoleServiceImpl implements SystemRoleService {
    @Override
    public Result<Long> add(SystemRole systemRole) {
        return null;
    }

    @Override
    public Result<Boolean> deleteById(Long id) {
        return null;
    }

    @Override
    public Result<Boolean> update(SystemRole systemRole) {
        return null;
    }

    @Override
    public Result<PagedData<SystemRole>> page(SystemRole systemRole) {
        return null;
    }

    @Override
    public Result<SystemRole> getUserInfoById(Long id) {
        return null;
    }
}
