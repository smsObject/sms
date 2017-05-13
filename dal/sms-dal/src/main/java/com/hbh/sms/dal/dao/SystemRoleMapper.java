package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.SystemRole;
import java.util.List;

/**
 * Created by hbh
 */
public interface SystemRoleMapper {
    int deleteByPrimaryKey(Long id);
    int insert(SystemRole record);
    int updateByPrimaryKey(SystemRole record);
    List<SystemRole> query(SystemRole record);
}