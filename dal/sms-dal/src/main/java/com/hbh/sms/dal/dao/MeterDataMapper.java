package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.MeterData;
import java.util.List;

public interface MeterDataMapper {
    public int insert(MeterData record);
    public List<MeterData> query(MeterData record);
}