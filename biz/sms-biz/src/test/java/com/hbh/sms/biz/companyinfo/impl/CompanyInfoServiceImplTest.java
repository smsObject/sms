package com.hbh.sms.biz.companyinfo.impl;

import com.hbh.sms.biz.BaseTest;
import com.hbh.sms.biz.service.CompanyInfo.CompanyInfoService;
import com.hbh.sms.model.entity.CompanyInfo;
import com.sms.common.PagedData;
import com.sms.common.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * CompanyInfoServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮһ�� 20, 2016</pre>
 */
public class CompanyInfoServiceImplTest extends BaseTest {

    @Autowired
    private CompanyInfoService companyInfoService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(CompanyInfo companyInfo)
     */
    @Test
    public void testAdd() throws Exception {
//TODO: Test goes here...
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyName("wo");
        companyInfoService.add(companyInfo);
    }

    /**
     * Method: deleteById(Long id)
     */
    @Test
    public void testDeleteById() throws Exception {
//TODO: Test goes here...
        companyInfoService.deleteById(2L);
    }

    /**
     * Method: update(CompanyInfo companyInfo)
     */
    @Test
    public void testUpdate() throws Exception {
//TODO: Test goes here...
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setCompanyName("qq");
        companyInfo.setId(2L);
        companyInfoService.update(companyInfo);
    }

    /**
     * Method: list(CompanyInfo companyInfo)
     */
    @Test
    public void testList() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: page(CompanyInfo companyInfo)
     */
    @Test
    public void testPage() throws Exception {
        CompanyInfo companyInfo = new CompanyInfo();
        companyInfo.setPageNo(1);
        companyInfo.setPageSize(10);
        Result<PagedData<CompanyInfo>> result = companyInfoService.page(companyInfo);
    }

    /**
     * Method: getCompanyInfoById(Long id)
     */
    @Test
    public void testGetCompanyInfoById() throws Exception {
//TODO: Test goes here...
        companyInfoService.getCompanyInfoById(2L);
    }

} 
