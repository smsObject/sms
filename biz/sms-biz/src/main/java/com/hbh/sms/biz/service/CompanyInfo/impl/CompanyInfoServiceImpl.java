package com.hbh.sms.biz.service.CompanyInfo.impl;

import com.hbh.sms.dal.dao.CompanyInfoMapper;
import com.hbh.sms.model.entity.CompanyInfo;
import com.hbh.sms.biz.service.CompanyInfo.CompanyInfoService;
import com.sms.common.PagedData;
import com.sms.common.Result;
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
        return null;
    }

    public Result<CompanyInfo> getCompanyInfoById(Long id) {
        return null;
    }
}