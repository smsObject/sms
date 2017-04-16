package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.CompayInfo;

public interface CompayInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CompayInfo record);

    int insertSelective(CompayInfo record);

    CompayInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CompayInfo record);

    int updateByPrimaryKey(CompayInfo record);
}