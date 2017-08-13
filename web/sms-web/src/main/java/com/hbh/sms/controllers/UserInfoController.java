package com.hbh.sms.controllers;

import com.hbh.sms.biz.service.userInfo.UserInfoService;
import com.hbh.sms.model.entity.UserInfo;
import com.hbh.sms.model.entity.UserPrice;
import com.sms.common.PagedData;
import com.sms.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * Created by hbh on
 */
@Controller
@RequestMapping("/sms/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/page")
    @ResponseBody
    public Result<PagedData<UserInfo>> page(UserInfo userInfo) {
        return userInfoService.page(userInfo);
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result<Long> add(UserInfo userInfo){
       return userInfoService.add(userInfo);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result<Boolean> update(UserInfo userInfo){
        return userInfoService.update(userInfo);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result<Boolean> delete(Long id){
        return userInfoService.deleteById(id);
    }

    @RequestMapping("/getUserWaterValue")
    @ResponseBody
    public Result<UserPrice> getUserWaterValue(UserPrice userPrice){
        return  userInfoService.getUserWaterValue(userPrice);
    }

    @RequestMapping("/buyWaterPrice")
    @ResponseBody
    public Result<Boolean> buyWaterPrice(UserPrice userPrice){
        return userInfoService.buyWaterValue(userPrice);
    }

    @RequestMapping("/calculateWaterPrice")
    @ResponseBody
    public Result<BigDecimal> calculateWaterPrice(Long userId,Float waterValue){
        return userInfoService.calculateWaterPrice(userId,waterValue);
    }

}