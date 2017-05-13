package com.hbh.sms.biz.service.system;

import com.hbh.sms.model.entity.SystemRole;
import com.sms.common.PagedData;
import com.sms.common.Result;

/**
 * Created by hbh
 */
public interface SystemRoleService {
    Result<Long> add(SystemRole systemRole);
    Result<Boolean> deleteById(Long id);
    Result<Boolean> update(SystemRole systemRole);
    Result<PagedData<SystemRole>> page(SystemRole systemRole);
    Result<SystemRole> getUserInfoById(Long id);
}