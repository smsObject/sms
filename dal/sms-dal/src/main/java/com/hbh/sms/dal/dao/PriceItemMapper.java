/**
 * qccr.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.PriceItem;

import java.util.List;

public interface PriceItemMapper {
    /**
     *
     * 删除单条记录
     */
    int delete(Long id);

    /**
     *
     * 增加单条记录
     */
    int insert(PriceItem record);

    /**
     *
     * 查询记录
     */
    List<PriceItem> query(PriceItem condition);

    /**
     *
     * 更新单条记录
     */
    int update(PriceItem record);
}