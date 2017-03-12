package com.hbh.sms.controllers;

import com.hbh.sms.biz.service.MeterData.MeterDataService;
import com.hbh.sms.biz.service.common.DataCenter;
import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.Meter;
import com.hbh.sms.model.entity.MeterData;
import com.hbh.sms.model.entity.SendMessageData;
import com.hbh.sms.biz.service.concentrator.ConcentratorService;
import com.hbh.sms.biz.service.message.BizDeviceService;
import com.hbh.sms.biz.service.meter.MeterService;
import com.sms.common.*;
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

    @Autowired
    private MeterDataService meterDataService;

    @RequestMapping("/meterManager")
    public String meterManager() {
        return "device/meterManager";
    }

    @RequestMapping("/gsmManager")
    public String gsmManager() {
        return "device/gsmManager";
    }

    @RequestMapping("/scanner")
    @ResponseBody
    public Result<List<Concentrator>> scanner() {
        Result<List<Concentrator>> result= bizDeviceService.scanner();
        if (result.isSuccess()){
            for (Concentrator concentrator :result.getData()){
                Concentrator concentrator1 = new Concentrator();
                concentrator1.setComPort(concentrator.getComPort());
                Result<List<Concentrator>> list=concentratorService.list(concentrator1);
                if (list.isSuccess() && list.getData().size() == 0){
                    concentratorService.add(concentrator);
                }else  if(list.isSuccess()){
                    concentratorService.updateByComPort(concentrator);
                }
            }
        }
        return result;
    }

    @RequestMapping("/readMeterData")
    @ResponseBody
    public Result readMeterData(Meter meter) {
        //获取GSM信息  查询仪表id查询仪表数据
        //获取发送对象
        Long id = meter.getId();
        if (id == null || id == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<Meter> result = meterService.getMeterById(id);
        if (result.getData() != null) {
            Meter meter1 = result.getData();
            Result<Concentrator> result1 = concentratorService.getConcentratorById(meter1.getId());
            if (result1.getData() != null) {
                Concentrator concentrator = result1.getData();
                SendMessageData messageData = new SendMessageData(meter1.getMeterCode(), DataCenter.READ_METER_CMD);
                if (bizDeviceService.sendMessage(concentrator, messageData))
                    return ResultUtil.newFailedResult(StateCode.SUCCESS);
            }
        }
        return ResultUtil.newFailedResult(StateCode.ERROR);
    }

    @RequestMapping("/setValveStatus")
    @ResponseBody
    public Result setValveStatus(Meter meter,Integer status){
        Long id = meter.getId();
        if (id == null || id == 0 || status== null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<Meter> result = meterService.getMeterById(id);
        if (result.getData() != null) {
            Meter meter1 = result.getData();
            Result<Concentrator> result1 = concentratorService.getConcentratorById(meter1.getId());
            if (result1.getData() != null) {
                Concentrator concentrator = result1.getData();
                SendMessageData messageData = null;
                if (status == 0){
                    messageData = new SendMessageData(meter1.getMeterCode(), DataCenter.CLOSE_VALVE_CMD);
                }else if (status == 1){
                    messageData = new SendMessageData(meter1.getMeterCode(), DataCenter.OPEN_VALVE_CMD);
                }
                if (bizDeviceService.sendMessage(concentrator, messageData))
                    return ResultUtil.newFailedResult(StateCode.SUCCESS);
            }
        }
        return ResultUtil.newFailedResult(StateCode.ERROR);
    }

    @RequestMapping("/getMeterData")
    @ResponseBody
    public Result<List<MeterData>> getMeterData(Long meterId){
        MeterData meterData = new MeterData();
        meterData.setMeterId(meterId);
        return meterDataService.list(meterData);
    }

    @RequestMapping("/concentratorPage")
    @ResponseBody
    public PageUtil gsmPage(Concentrator concentrator) {
        Result<PagedData<Concentrator>> result = concentratorService.page(concentrator);
        PageUtil pageUtil = new PageUtil();
        //pageUtil.setRows(result.getData());
        pageUtil.setTotal(result.getTotalCount());
        return pageUtil;
    }

    @RequestMapping("/addConcentrator")
    public Result addConcentrator(Concentrator concentrator) {
        try {
            concentratorService.add(concentrator);
            return ResultUtil.newSuccessResult(StateCode.SUCCESS);
        } catch (Exception e) {
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    @RequestMapping("/meterPage")
    @ResponseBody
    public PageUtil meterPage(Meter meter) {
        Result<PagedData<Meter>> result = meterService.page(meter);
        PageUtil pageUtil = new PageUtil();
       // pageUtil.setRows(result.getData());
        pageUtil.setTotal(result.getTotalCount());
        return pageUtil;
    }

    @RequestMapping("/addMeter")
    public Result addMeter(Meter meter) {
        if (meter.getMeterNo() != null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            meterService.add(meter);
            return ResultUtil.newSuccessResult(StateCode.SUCCESS);
        } catch (Exception e) {
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    @RequestMapping("/deleteMeter")
    public Result deleteMeter(Long id) {
        if (id == null || id.longValue() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            meterService.delete(id);
            return ResultUtil.newSuccessResult(StateCode.SUCCESS);
        } catch (Exception e) {
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    @RequestMapping("/updateMeter")
    public Result<Boolean> updateMeter(Meter meter){
        if (meter == null || meter.getId() == null || meter.getId().longValue() == 0){
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
       Result<Boolean> result =  meterService.update(meter);
        return result;
    }
}