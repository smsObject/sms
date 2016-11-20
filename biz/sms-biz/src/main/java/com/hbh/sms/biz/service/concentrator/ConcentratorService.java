package com.hbh.sms.biz.service.concentrator;

import com.hbh.sms.model.entity.Concentrator;
import com.sms.common.PagedData;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
public interface ConcentratorService {
      Result<Long> add(Concentrator concentrator);
      Result<List<Concentrator>> list(Concentrator concentrator);
      Result<Concentrator> getConcentratorById(Long id);
      Result<Boolean> update(Concentrator concentrator);
      Result<Boolean>  delete(Long id);
      Result<PagedData<Concentrator>> page(Concentrator concentrator);
      Result<Boolean>  updateByComPort(Concentrator concentrato);
}
