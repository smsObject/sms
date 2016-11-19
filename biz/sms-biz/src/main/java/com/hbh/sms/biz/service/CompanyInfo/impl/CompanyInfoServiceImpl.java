package com.hbh.sms.biz.service.CompanyInfo.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.dal.dao.CompanyInfoMapper;
import com.hbh.sms.model.entity.CompanyInfo;
import com.hbh.sms.biz.service.CompanyInfo.CompanyInfoService;
import com.sms.common.PagedData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 2016/10/15.
 */
@Service("companyInfoService")
public class CompanyInfoServiceImpl implements CompanyInfoService {
    @Autowired
    private CompanyInfoMapper companyInfoMapper;

    public Result<Long> add(CompanyInfo companyInfo) {
        return null;
    }

    public Result<Boolean> deleteById(Long id) {
        return null;
    }

    public Result<Boolean> update(CompanyInfo companyInfo) {
        return null;
    }

    public Result<List<CompanyInfo>> list(CompanyInfo companyInfo) {
        return null;
    }

    public Result<PagedData<CompanyInfo>> page(CompanyInfo companyInfo) {
        if (companyInfo == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数缺失");
        }
        Result<PagedData<CompanyInfo>> result = null;
        Page page = PageHelper.startPage(companyInfo.getPageNo(), companyInfo.getPageSize());
        List<CompanyInfo> companyInfos = companyInfoMapper.query(companyInfo);
        PagedData<CompanyInfo> pagedData = new PagedData<>();
        pagedData.setPageNo(companyInfo.getPageNo());
        pagedData.setPageSize(companyInfo.getPageSize());
        pagedData.setTotalSize(page.getTotal());
        pagedData.setResultList(companyInfos);
        result = ResultUtil.newSuccessResult(pagedData);
        return result;
    }

    public Result<CompanyInfo> getCompanyInfoById(Long id) {
        return null;
    }
}