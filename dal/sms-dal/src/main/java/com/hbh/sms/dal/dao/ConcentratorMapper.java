package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.Concentrator;

import java.util.List;

public interface ConcentratorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Concentrator record);

    int insertSelective(Concentrator record);

    Concentrator selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Concentrator record);

    int updateByPrimaryKey(Concentrator record);

    int count(Concentrator record);

    List<Concentrator> list(Concentrator record);

    List<Concentrator> page(Concentrator record);

}