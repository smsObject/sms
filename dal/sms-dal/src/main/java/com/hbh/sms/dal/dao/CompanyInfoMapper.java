package com.hbh.sms.dal.dao;

import com.hbh.sms.model.entity.CompanyInfo;

import java.util.List;

public interface CompanyInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CompanyInfo record);

    int insertSelective(CompanyInfo record);

    CompanyInfo selectByPrimaryKey(Long id);

//    int updateByPrimaryKeySelective(companyInfo record);

    int updateByPrimaryKey(CompanyInfo record);

    List<CompanyInfo> query(CompanyInfo companyInfo);
}