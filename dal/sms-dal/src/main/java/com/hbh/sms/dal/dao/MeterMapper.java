package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.Meter;

import java.util.List;

public interface MeterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Meter record);

//    int insertSelective(Meter record);

    Meter selectByPrimaryKey(Long id);

//    int updateByPrimaryKeySelective(Meter record);

    int updateByPrimaryKey(Meter record);

    List<Meter> query(Meter record);

}