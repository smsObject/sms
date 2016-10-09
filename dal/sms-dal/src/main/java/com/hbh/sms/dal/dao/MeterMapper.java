package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.Meter;

import java.util.List;

public interface MeterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Meter record);

    int insertSelective(Meter record);

    Meter selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Meter record);

    int updateByPrimaryKey(Meter record);

    int count(Meter record);

    List<Meter> list(Meter record);

}