package com.hbh.sms.controllers;

import com.hbh.sms.biz.service.setting.PriceService;
import com.hbh.sms.model.entity.Price;
import com.hbh.sms.model.entity.PriceTemplate;
import com.sms.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * Created by hbh on 2017/6/13.
 */

@RequestMapping("sms/setting")
@Controller
public class SettingController {

    @Autowired
    private PriceService priceService;

    // -----------------费用模版----------------------------//
    @RequestMapping("/addAndUpdatePriceTemplate")
    @ResponseBody
    public Result<Long> addPriceTemplate(PriceTemplate priceTemplate) {
        Result<Long> result = priceService.addAndUpdatePriceTemplate(priceTemplate);
        return result;
    }

    @RequestMapping("deletePriceTemplate")
    @ResponseBody
    public Result<Boolean> deletePriceTemplate(Long id) {
        Result<Boolean> result = priceService.deletePriceTemplate(id);
        return result;
    }

    @RequestMapping("/listPriceTemplates")
    @ResponseBody
    public Result<List<PriceTemplate>> listPriceTemplates() {
        Result<List<PriceTemplate>> result = priceService.listPriceTemplates();
        return result;
    }

    //----------------------费用----------------------------//

    @RequestMapping("/addPrice")
    @ResponseBody
    public Result<Long> addPrice(Price price) {
        Result<Long> result = priceService.addPrice(price);
        return result;
    }

    @RequestMapping("/deletePrice")
    @ResponseBody
    public Result<Boolean> deletePrice(Long id) {
        Result<Boolean> result = priceService.deletePrice(id);
        return result;
    }

    @RequestMapping("/updatePrice")
    @ResponseBody
    public Result<Boolean> updatePrice(Price price) {
        Result<Boolean> result = priceService.updatePrice(price);
        return result;
    }

    @RequestMapping("/listPrices")
    @ResponseBody
    public Result<List<Price>> listPrices() {
        Result<List<Price>> result = priceService.listPrices();
        return result;
    }

}