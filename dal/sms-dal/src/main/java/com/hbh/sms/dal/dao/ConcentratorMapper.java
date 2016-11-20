package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.Concentrator;

import java.util.List;

public interface ConcentratorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Concentrator record);

//    int insertSelective(Concentrator record);

    Concentrator selectByPrimaryKey(Long id);

//    int updateByPrimaryKeySelective(Concentrator record);

    int updateByPrimaryKey(Concentrator record);

    List<Concentrator> query(Concentrator record);

    int  updateByComPort(Concentrator concentrator);
}