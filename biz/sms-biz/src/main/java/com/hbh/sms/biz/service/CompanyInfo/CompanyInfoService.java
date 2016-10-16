package com.hbh.sms.biz.service.CompanyInfo;

import com.hbh.sms.model.entity.CompanyInfo;
import com.sms.common.Result;

import java.util.List;

/**
 * Created by user on 2016/10/15.
 */

public interface CompanyInfoService {
    Result<Long> add(CompanyInfo companyInfo);
    Result<Boolean> deleteById(Long id);
    Result<Boolean> update(CompanyInfo companyInfo);
    Result<List<CompanyInfo>> list(CompanyInfo companyInfo);
    Result<List<CompanyInfo>> page(CompanyInfo companyInfo);
    Result<CompanyInfo> getCompanyInfoById(Long id);
}