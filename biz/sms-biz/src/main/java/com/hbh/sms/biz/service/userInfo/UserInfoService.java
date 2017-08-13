package com.hbh.sms.biz.service.userInfo;

import com.hbh.sms.model.entity.UserInfo;
import com.hbh.sms.model.entity.UserPrice;
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
    Result<UserInfo> getUserInfoById(Long id);
    Result<UserPrice> getUserWaterValue(UserPrice userPrice);
    Result<Boolean> buyWaterValue(UserPrice userPrice);
}