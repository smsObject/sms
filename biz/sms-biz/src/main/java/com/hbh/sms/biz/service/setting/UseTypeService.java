package com.hbh.sms.biz.service.setting;

import com.hbh.sms.model.entity.UseType;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2017/7/9.
 */
public interface UseTypeService {

    Result<List<UseType>> listUseType(UseType useType);

    Result<Long> addUseType(UseType useType);

    Result<Boolean> updateUseType(UseType useType);

    Result<Boolean> deleteUseType(Long id);
}
