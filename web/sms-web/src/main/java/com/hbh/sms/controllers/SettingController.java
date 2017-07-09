package com.hbh.sms.controllers;

import com.alibaba.fastjson.JSON;
import com.hbh.sms.biz.service.setting.PriceService;
import com.hbh.sms.biz.service.setting.UseTypeService;
import com.hbh.sms.model.entity.Price;
import com.hbh.sms.model.entity.PriceTemplate;
import com.hbh.sms.model.entity.UseType;
import com.sms.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by hbh on 2017/6/13.
 */

@RequestMapping("sms/setting")
@Controller
public class SettingController {

    @Autowired
    private PriceService priceService;
    @Autowired
    private UseTypeService useTypeService;

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
    public Result<Long> addPrice(HttpServletRequest request,Price price) {
        Price price1 = JSON.parseObject(request.getParameter("data"), Price.class);
        Result<Long> result = priceService.addPrice(price);
        return result;
    }

    @RequestMapping("/queryPriceNames")
    @ResponseBody
    public Result<Map<Long ,String>> queryPriceNames(){
        Result<Map<Long ,String>> result = priceService.queryPriceNames();
        return result;
    }

    @RequestMapping("/getPriceById")
    @ResponseBody
    public Result<Price> getPriceById(Long id){
        Result<Price> result = priceService.getPriceById(id);
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
    public Result<List<Price>> listPrices(Price price) {
        Result<List<Price>> result = priceService.listPrices(price);
        return result;
    }

    @RequestMapping("/listUseType")
    @ResponseBody
    public Result<List<UseType>> listUseType(UseType useType){
        Result<List<UseType>> result = useTypeService.listUseType(useType);
        return result;
    }

    @RequestMapping("/addUseType")
    @ResponseBody
    public Result<Long> addUseType(UseType useType){
        Result<Long> result = useTypeService.addUseType(useType);
        return result;
    }

    @RequestMapping("/updateUseType")
    @ResponseBody
    public Result<Boolean> updateUseType(UseType useType){
        Result<Boolean> result = useTypeService.updateUseType(useType);
        return result;
    }

    @RequestMapping("/deleteUseType")
    @ResponseBody
    public Result<Boolean> deleteUseType(Long id){
        Result<Boolean> result = useTypeService.deleteUseType(id);
        return result;
    }
}