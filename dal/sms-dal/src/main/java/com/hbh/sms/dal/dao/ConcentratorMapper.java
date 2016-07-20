package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.Concentrator;

public interface ConcentratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Concentrator record);

    int insertSelective(Concentrator record);

    Concentrator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Concentrator record);

    int updateByPrimaryKey(Concentrator record);

    int count(Concentrator reccord);

}