/**
 * qccr.com Inc.
 * Copyright (c) 2014-2017 All Rights Reserved.
 */
package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.PriceemplateDO;
import java.util.List;

public interface PriceemplateDao {
    /**
     *
     * 删除单条记录
     */
    int delete(Long id);

    /**
     *
     * 增加单条记录
     */
    int insert(PriceemplateDO record);

    /**
     *
     * 查询记录
     */
    List<PriceemplateDO> query(PriceemplateDO condition);

    /**
     *
     * 更新单条记录
     */
    int update(PriceemplateDO record);
}