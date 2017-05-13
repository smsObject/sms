package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.UserInfo;
import java.util.List;

public interface UserInfoMapper {
    int deleteByPrimaryKey(Long id);
    int insert(UserInfo record);
    int updateByPrimaryKey(UserInfo record);
    List<UserInfo> query(UserInfo record);
}