/**
 * qccr.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.Price;
import java.util.List;

public interface PriceMapper {
    /**
     *
     * 删除单条记录
     */
    int delete(Long id);

    /**
     *
     * 增加单条记录
     */
    int insert(Price record);

    /**
     *
     * 查询记录
     */
    List<Price> query();

    /**
     *
     * 更新单条记录
     */
    int update(Price record);
}