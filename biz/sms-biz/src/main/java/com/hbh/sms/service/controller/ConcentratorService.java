package com.hbh.sms.service.controller;

import com.hbh.sms.model.entity.Concentrator;

import java.util.List;

/**
 * Created by hbh on 2016/7/19.
 */
public interface ConcentratorService {
    public void add(Concentrator concentrator);
    public List<Concentrator> list(Concentrator concentrator);
    public void update(Concentrator concentrator);
    public void  delete(Integer id);
    public List<Concentrator> page(Concentrator concentrator);
}
