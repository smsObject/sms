package com.hbh.sms.biz.service.concentrator;

import com.hbh.sms.model.entity.Concentrator;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
public interface ConcentratorService {
      Result<Integer> add(Concentrator concentrator);
      Result<List<Concentrator>> list(Concentrator concentrator);
      Result<Concentrator> getConcentratorById(Integer id);
      Result<Boolean> update(Concentrator concentrator);
      Result<Boolean>  delete(Integer id);
      Result<List<Concentrator>> page(Concentrator concentrator);
      Result<Boolean>  updateByComPort(Concentrator concentrato);
}
