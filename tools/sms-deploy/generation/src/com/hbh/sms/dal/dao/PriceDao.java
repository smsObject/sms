/**
 * qccr.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.PriceDO;
import java.util.List;

public interface PriceDao {
    /**
     *
     * 删除单条记录
     */
    int delete(Long id);

    /**
     *
     * 增加单条记录
     */
    int insert(PriceDO record);

    /**
     *
     * 查询记录
     */
    List<PriceDO> query(PriceDO condition);

    /**
     *
     * 更新单条记录
     */
    int update(PriceDO record);
}