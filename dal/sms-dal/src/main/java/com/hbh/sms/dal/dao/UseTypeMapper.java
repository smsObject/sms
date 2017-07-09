package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.UseType;
import java.util.List;

/**
 * Created by hbh on 2017/7/9.
 */
public interface UseTypeMapper {
    int deleteByPrimaryKey(Long id);
    int insert(UseType record);
    int updateByPrimaryKey(UseType record);
    List<UseType> query(UseType record);
}