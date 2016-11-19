package com.hbh.sms.controllers;

import com.hbh.sms.biz.service.CompanyInfo.CompanyInfoService;
import com.hbh.sms.model.entity.CompanyInfo;
import com.sms.common.PagedData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by user on 2016/11/13.
 */
@Controller()
@RequestMapping("companyInfo")
public class CompanyInfoController {
    @Autowired
    private CompanyInfoService companyInfoService;

    @RequestMapping("/add")
    public Result<Long> addCompanyInfo(CompanyInfo companyInfo){
        if (companyInfo == null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            return  companyInfoService.add(companyInfo);
        }catch (Exception ex){
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    @RequestMapping("/page")
    @ResponseBody
    public Result<PagedData<CompanyInfo>> page(CompanyInfo companyInfo){
        if (companyInfo == null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<PagedData<CompanyInfo>> result = null;
        try {
            result = companyInfoService.page(companyInfo) ;
        }catch (Exception ex){
            result = ResultUtil.newFailedResult(StateCode.ERROR);
        }
        return result;
    }
}