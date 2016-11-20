package com.hbh.sms.biz.service.UserInfo;

import com.hbh.sms.model.entity.UserInfo;
import com.sms.common.PagedData;
import com.sms.common.Result;

/**
 * Created by user on 2016/11/20.
 */
public interface UserInfoService {
    Result<Long> add(UserInfo userInfo);
    Result<Boolean> deleteById(Long id);
    Result<Boolean> update(UserInfo userInfo);
    Result<PagedData<UserInfo>> page(UserInfo userInfo);
    Result<UserInfo> getCompanyInfoById(Long id);
}