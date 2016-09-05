package com.hbh.sms.service.concentrator;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.Meter;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
public interface ConcentratorService {
      void add(Concentrator concentrator);
      Result<List<Concentrator>> list(Concentrator concentrator);
      Result<Concentrator> getConcentratorById(Integer id);
      void update(Concentrator concentrator);
      void  delete(Integer id);
      Result<List<Concentrator>> page(Concentrator concentrator);
      void  updateByComPort(Concentrator concentrato);
}
