package com.hbh.sms.controllers;

import com.hbh.sms.biz.service.MeterData.MeterDataService;
import com.hbh.sms.model.entity.MeterData;
import com.sms.common.PageUtil;
import com.sms.common.PagedData;
import com.sms.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2016/8/16.
 */
@Controller
@RequestMapping("/meterData")
public class MeterDataController {

    @Autowired
    private MeterDataService meterDataService;

    @RequestMapping("meterDataManager")
    public String meterDataManager() {
        return "data/meterData";
    }

    @RequestMapping("page")
    @ResponseBody
    public PageUtil page(MeterData meterData) {
        Result<PagedData<MeterData>> result = meterDataService.list(meterData);
        PageUtil pageUtil = new PageUtil();
       // pageUtil.setRows(result.getData());
        pageUtil.setTotal(result.getTotalCount());
        return pageUtil;
    }
}