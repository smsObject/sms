package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.MeterData;
import java.util.List;

public interface MeterDataMapper {
    int insert(MeterData record);

    int insertSelective(MeterData record);

    List<MeterData> list(MeterData record);
}