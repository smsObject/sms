package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.SystemUser;
import java.util.List;

/**
 * Created by hbh
 */
public interface SystemUserMapper {
    int deleteByPrimaryKey(Long id);
    int insert(SystemUser record);
    int updateByPrimaryKey(SystemUser record);
    List<SystemUser> query(SystemUser record);
}