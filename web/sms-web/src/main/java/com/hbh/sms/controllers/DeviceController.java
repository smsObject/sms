package com.hbh.sms.controllers;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.Meter;
import com.hbh.sms.model.entity.SendMessageData;
import com.hbh.sms.service.concentrator.ConcentratorService;
import com.hbh.sms.service.message.BizDeviceService;
import com.hbh.sms.service.meter.MeterService;
import com.sms.common.PageUtil;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by hbh on 2016/7/17.
 */

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private BizDeviceService bizDeviceService;

    @Autowired
    private MeterService meterService;

    @Autowired
    private ConcentratorService concentratorService;

    @RequestMapping("/meterManager")
    public String meterManager(){
        return "device/meterManager";
    }

    @RequestMapping("/gsmManager")
    public String gsmManager(){
        return "device/gsmManager";
    }

    @RequestMapping("/scanner")
    @ResponseBody
    public Result<List<Concentrator>> scanner(){
       return bizDeviceService.scanner();
    }

    @RequestMapping("/readMeterData")
    public Result readMeterData(Meter meter){
        //获取GSM信息  查询仪表id查询仪表数据
        //获取发送对象

        Integer id= meter.getId();
        if (id == null || id == 0){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
            Result<Meter> result= meterService.getMeterById(id);
            if (result.getData() != null ){
                Meter meter1= result.getData();
                Result<Concentrator> result1 =concentratorService.getConcentratorById(meter1.getId());
                if (result1.getData() != null){
                    Concentrator concentrator= result1.getData();
                    SendMessageData messageData = new SendMessageData(meter1.getMeterCode() , "0013");
                    if (bizDeviceService.sendMessage(concentrator , messageData))
                        return ResultUtil.newFailedResult(StateCode.SUCCESS);
                }
            }
        return ResultUtil.newFailedResult(StateCode.ERROR);
    }

    @RequestMapping("/meterPage")
    @ResponseBody
    public PageUtil meterPage(Meter meter)
    {
        Result<List<Meter>> result=  meterService.page(meter);
        PageUtil pageUtil = new PageUtil();
        pageUtil.setRows(result.getData());
        pageUtil.setTotal(result.getTotalCount());
        return  pageUtil;
    }

    @RequestMapping("/concentratorPage")
    @ResponseBody
    public Result<List<Concentrator>> gsmPage(Concentrator concentrator){
        return concentratorService.page(concentrator);
    }

    @RequestMapping("/addMeter")
    public Result addMeter(Meter meter){
        if (meter.getMeterNo() != null){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            meterService.add(meter);
            return ResultUtil.newSuccessResult(StateCode.SUCCESS);
        }catch (Exception e){
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    @RequestMapping("/addConcentrator")
    public Result addConcentrator (Concentrator concentrator){
        try {
            concentratorService.add(concentrator);
            return ResultUtil.newSuccessResult(StateCode.SUCCESS);
        }catch (Exception e){
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    @RequestMapping("/deleteMeter")
    public Result deleteMeter(Integer id){
        if (id == null || id == 0){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            meterService.delete(id);
            return ResultUtil.newSuccessResult(StateCode.SUCCESS);
        }catch (Exception e){
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

}