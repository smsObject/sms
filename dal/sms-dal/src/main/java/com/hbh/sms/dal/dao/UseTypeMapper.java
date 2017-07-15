package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.UseType;
import java.util.List;

/**
 * Created by hbh on 2017/7/9.
 */
public interface UseTypeMapper {
    int delete(Long id);
    int insert(UseType record);
    int update(UseType record);
    List<UseType> query(UseType record);
}