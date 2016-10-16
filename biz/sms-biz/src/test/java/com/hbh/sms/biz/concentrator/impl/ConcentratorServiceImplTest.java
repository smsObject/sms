package com.hbh.sms.biz.concentrator.impl;

import com.hbh.sms.biz.BaseTest;
import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.biz.service.concentrator.ConcentratorService;
import com.sms.common.Result;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * ConcentratorServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>ʮ�� 6, 2016</pre>
 */

public class ConcentratorServiceImplTest extends BaseTest {

    @Autowired
    private ConcentratorService concentratorService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: add(Concentrator concentrator)
     */
    @Test
    public void testAdd() throws Exception {
        Concentrator concentrator = new Concentrator();
        concentrator.setComPort("com4");
        concentrator.setModel("SMS1");
        concentrator.setBaudRate(9999);
        concentrator.setManufacturer("SMS");
        concentrator.setIsOnline(1);//在线
        concentrator.setCreateUser("me");
        concentrator.setCode("ABAC");
        Result<Integer> result = concentratorService.add(concentrator);
    }

    /**
     * Method: list(Concentrator concentrator)
     */
    @Test
    public void testList() throws Exception {
    }

    /**
     * Method: getConcentratorById(Integer id)
     */
    @Test
    public void testGetConcentratorById() throws Exception {
    }

    /**
     * Method: update(Concentrator concentrator)
     */
    @Test
    public void testUpdate() throws Exception {
    }

    /**
     * Method: delete(Integer id)
     */
    @Test
    public void testDelete() throws Exception {
    }

    @Test
    public void testPage() throws Exception {
        Concentrator concentrator = new Concentrator();
        Result<List<Concentrator>> result  = concentratorService.page(concentrator);
    }

    /**
     * Method: updateByComPort(Concentrator concentrator)
     */
    @Test
    public void testUpdateByComPort() throws Exception {
    }
}