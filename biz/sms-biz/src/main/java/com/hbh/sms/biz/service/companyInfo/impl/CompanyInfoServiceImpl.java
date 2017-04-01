package com.hbh.sms.biz.service.companyInfo.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hbh.sms.dal.dao.CompanyInfoMapper;
import com.hbh.sms.model.entity.CompanyInfo;
import com.hbh.sms.biz.service.companyInfo.CompanyInfoService;
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
        if (companyInfo == null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED,"参数缺失");
        }
        int i = companyInfoMapper.insert(companyInfo);
        return ResultUtil.newSuccessResult(companyInfo.getId());
    }

    public Result<Boolean> deleteById(Long id) {
        if (id == null || id.longValue() == 0){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数缺失");
        }
        int i = companyInfoMapper.deleteByPrimaryKey(id);
        return ResultUtil.newSuccessResult(i > 0);
    }

    public Result<Boolean> update(CompanyInfo companyInfo) {
        if (companyInfo == null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED,"参数缺失");
        }
        int i = companyInfoMapper.updateByPrimaryKey(companyInfo);
        return ResultUtil.newSuccessResult(i > 0);
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
        if (id == null || id.longValue() == 0){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数缺失");
        }
        CompanyInfo companyInfo = companyInfoMapper.selectByPrimaryKey(id);
        return ResultUtil.newSuccessResult(companyInfo);
    }

}