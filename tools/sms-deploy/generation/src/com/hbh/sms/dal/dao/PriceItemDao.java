/**
 * qccr.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.PriceItemDO;
import java.util.List;

public interface PriceItemDao {
    /**
     *
     * 删除单条记录
     */
    int delete(Long id);

    /**
     *
     * 增加单条记录
     */
    int insert(PriceItemDO record);

    /**
     *
     * 查询记录
     */
    List<PriceItemDO> query(PriceItemDO condition);

    /**
     *
     * 更新单条记录
     */
    int update(PriceItemDO record);
}