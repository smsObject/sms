package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.UserInfo;
import com.hbh.sms.model.entity.UserPrice;

import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);
    int insert(UserInfo record);
    int updateByPrimaryKey(UserInfo record);
    List<UserInfo> query(UserInfo record);
    UserPrice getUserWaterValue(UserPrice userPrice);
    UserPrice getLastBuyWaterValue(UserPrice userPrice);
    int saveBuyWaterValue(UserPrice userPrice);
    UserInfo get(UserInfo search);
}