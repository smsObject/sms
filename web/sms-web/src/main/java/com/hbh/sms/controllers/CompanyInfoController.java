package com.hbh.sms.controllers;

import com.hbh.sms.biz.service.companyInfo.CompanyInfoService;
import com.hbh.sms.model.entity.CompanyInfo;
import com.sms.common.PagedData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sms/companyInfo")
public class CompanyInfoController {
    @Autowired
    private CompanyInfoService companyInfoService;

    @RequestMapping("/list")
    public String list(){
        return  "companyInfo/list";
    }

    @RequestMapping("/add")
    @ResponseBody
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

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> deleteCompanyInfo(Long id){
        if (id == null || id.longValue() == 0){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try{
            return  companyInfoService.deleteById(id);
        }catch (Exception ex){
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    @RequestMapping("/updateCompanyInfo")
    public Result<Boolean> updateCompanyInfo(CompanyInfo companyInfo){
        if ( companyInfo == null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            return companyInfoService.update(companyInfo);
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
            ex.printStackTrace();
            result = ResultUtil.newFailedResult(StateCode.ERROR);
        }
        return result;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result<Boolean> update(CompanyInfo companyInfo){
        if (companyInfo == null || companyInfo.getId() == null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<Boolean> result =  null;
        try {
            result = companyInfoService.update(companyInfo);
        }catch (Exception ex){
            result = ResultUtil.newFailedResult(StateCode.ERROR);
        }
        return result;
    }
}