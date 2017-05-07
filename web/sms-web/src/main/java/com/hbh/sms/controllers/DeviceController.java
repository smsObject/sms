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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    //扫描设备
    @RequestMapping("/scanner")
    @ResponseBody
    public Result<List<Concentrator>> scanner() {
        Result<List<Concentrator>> result = bizDeviceService.scanner();
        if (result.isSuccess()) {
            for (Concentrator concentrator : result.getData()) {
                Concentrator concentrator1 = new Concentrator();
                concentrator1.setComPort(concentrator.getComPort());
                concentrator.setCode("hbh" + concentrator.getComPort());
                concentrator.setCreatePerson("system");
                concentrator.setUpdatePerson("system");
                concentrator.setName(concentrator.getComPort());
                Result<List<Concentrator>> list = concentratorService.list(concentrator1);
                if (list.isSuccess() && list.getData().size() == 0) {
                    concentratorService.add(concentrator);
                } else if (list.isSuccess()) {
                    concentratorService.updateByComPort(concentrator);
                }
            }
        }
        return result;
    }

    //发送短信 读数据命令
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
            Result<Concentrator> result1 = concentratorService.getConcentratorById(meter1.getControllerId());
            if (result1.getData() != null) {
                Concentrator concentrator = result1.getData();
                SendMessageData messageData = new SendMessageData(meter1.getMeterCode(), DataCenter.READ_METER_CMD);
                if (bizDeviceService.sendMessage(concentrator, messageData))
                    return ResultUtil.newFailedResult(StateCode.SUCCESS);
            }
        }
        return ResultUtil.newFailedResult(StateCode.SUCCESS);
    }

    //发送数据 设置阀的状态值
    @RequestMapping("/setValveStatus")
    @ResponseBody
    public Result setValveStatus(Meter meter, Integer status) {
        Long id = meter.getId();
        if (id == null || id == 0 || status == null) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<Meter> result = meterService.getMeterById(id);
        if (result.getData() != null) {
            Meter meter1 = result.getData();
            Result<Concentrator> result1 = concentratorService.getConcentratorById(meter1.getControllerId());
            if (result1.getData() != null) {
                Concentrator concentrator = result1.getData();
                SendMessageData messageData = null;
                if (status == 0) {
                    messageData = new SendMessageData(meter1.getMeterCode(), DataCenter.CLOSE_VALVE_CMD);
                } else if (status == 1) {
                    messageData = new SendMessageData(meter1.getMeterCode(), DataCenter.OPEN_VALVE_CMD);
                }
                if (bizDeviceService.sendMessage(concentrator, messageData))
                    return ResultUtil.newFailedResult(StateCode.SUCCESS);
            }
        }
        return ResultUtil.newFailedResult(StateCode.ERROR);
    }

    @RequestMapping("/setTiming")
    public Result setTiming(Long meterId, String day1, String day2, String day3,String day4,
                            String timing1, String timing2, String timing3,String timing4,
                            Boolean on1, Boolean on2, Boolean on3, Boolean on4) {
        if (meterId == null || meterId == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }

        Result<Meter> result = meterService.getMeterById(meterId);
        Concentrator concentrator = null;
        Meter meter1 = null;
        if (result.getData() != null) {
            meter1 = result.getData();
            Result<Concentrator> result1 = concentratorService.getConcentratorById(meter1.getControllerId());
            if (result1.getData() != null) {
                concentrator = result1.getData();
            } else {
                return ResultUtil.newFailedResult(StateCode.ERROR, "没有关联的GSM设备");
            }
        } else {
            return ResultUtil.newFailedResult(StateCode.ERROR, "没有该仪表设备");
        }

        String cmd1 = "000000";
        if (on1) {
            if ((day1 != null && !"0".equals(day1)) && (timing1 != null || timing1.trim().length() > 0)) {
                try {
                    timing1 = timing1.substring(16, 21);
                    String[] strs = timing1.split(":");
                    cmd1 = day1 + strs[0] + strs[1];
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return ResultUtil.newFailedResult(StateCode.ERROR, "时间1输入格式异常");
                }
            } else {
                return ResultUtil.newFailedResult(StateCode.ERROR, "时间1输入格式异常");
            }
        }

        String cmd2 = "000000";
        if (on2){
            if ((day2 != null && !"0".equals(day2)) && (timing2 != null || timing2.trim().length() > 0)) {
                try {
                    timing2 = timing2.substring(16, 21);
                    String[] strs = timing2.split(":");
                    cmd2 = day2 + strs[0] + strs[1];
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return ResultUtil.newFailedResult(StateCode.ERROR, "时间1输入格式异常");
                }
            } else {
                return ResultUtil.newFailedResult(StateCode.ERROR, "时间1输入格式异常");
            }
        }

        String cmd3 = "000000";
        if (on3){
            if ((day3 != null && !"0".equals(day3)) && (timing3 != null || timing3.trim().length() > 0)) {
                try {
                    timing3 = timing3.substring(16, 21);
                    String[] strs = timing3.split(":");
                    cmd3 = day3 + strs[0] + strs[1];
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return ResultUtil.newFailedResult(StateCode.ERROR, "时间1输入格式异常");
                }
            } else {
                return ResultUtil.newFailedResult(StateCode.ERROR, "时间1输入格式异常");
            }
        }

        String cmd4 = "000000";
        if (on4){
            if ((day4 != null && !"0".equals(day4)) && (timing4 != null || timing4.trim().length() > 0)) {
                try {
                    timing4 = timing4.substring(16, 21);
                    String[] strs = timing4.split(":");
                    cmd4 = day4 + strs[0] + strs[1];
                } catch (Exception ex) {
                    ex.printStackTrace();
                    return ResultUtil.newFailedResult(StateCode.ERROR, "时间1输入格式异常");
                }
            } else {
                return ResultUtil.newFailedResult(StateCode.ERROR, "时间1输入格式异常");
            }
        }

        SendMessageData messageData = new SendMessageData(meter1.getMeterCode(), DataCenter.getSetTimingCmd(cmd1,cmd2,cmd3,cmd4));
            if (bizDeviceService.sendMessage(concentrator, messageData))
                return ResultUtil.newFailedResult(StateCode.SUCCESS);

        return ResultUtil.newFailedResult(StateCode.ERROR);
    }

    @RequestMapping("/getMeterData")
    @ResponseBody
    public Result<List<MeterData>> getMeterData(Long meterId) {
        MeterData meterData = new MeterData();
        meterData.setMeterId(meterId);
        return meterDataService.list(meterData);
    }

    @RequestMapping("/concentratorPage")
    @ResponseBody
    public Result<PagedData<Concentrator>> gsmPage(Concentrator concentrator) {
        Result<PagedData<Concentrator>> result = concentratorService.page(concentrator);
        return result;
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
    public Result<PagedData<Meter>> meterPage(Meter meter) {
        Result<PagedData<Meter>> result = meterService.page(meter);
        return result;
    }

    @RequestMapping("/addMeter")
    @ResponseBody
    public Result addMeter(Meter meter) {
        if (meter.getMeterCode() == null || meter.getMeterCode().trim().length() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        try {
            meter.setCreatePerson("system");
            meterService.add(meter);
            return ResultUtil.newSuccessResult(StateCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
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
    public Result<Boolean> updateMeter(Meter meter) {
        if (meter == null || meter.getId() == null || meter.getId().longValue() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }
        Result<Boolean> result = meterService.update(meter);
        return result;
    }

    @RequestMapping("/setManagerCenter")
    public Result<Boolean> setMenegerCenter(Long meterId, String mc1, String mc2, String mc3) {
        if ((meterId == null || meterId.longValue() == 0)) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "参数缺失");
        }

        if (mc1 == null || mc1.trim().length() == 0 ||
                mc2 == null || mc1.trim().length() == 0 ||
                mc3 == null || mc3.trim().length() == 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED);
        }

        Result<Meter> result = meterService.getMeterById(meterId);
        Concentrator concentrator = null;
        Meter meter1 = null;

        if (result.getData() != null) {
            meter1 = result.getData();
            Result<Concentrator> result1 = concentratorService.getConcentratorById(meter1.getControllerId());
            if (result1.getData() != null) {
                concentrator = result1.getData();
            } else {
                return ResultUtil.newFailedResult(StateCode.ERROR, "没有关联的GSM设备");
            }
        } else {
            return ResultUtil.newFailedResult(StateCode.ERROR, "没有该仪表设备");
        }

        String cmd = DataCenter.getSetManagerCenterCmd(mc1, mc2, mc3);
        System.out.println(cmd);
        SendMessageData messageData = new SendMessageData(meter1.getMeterCode(), cmd);
        if (bizDeviceService.sendMessage(concentrator, messageData)) {
            return ResultUtil.newFailedResult(StateCode.SUCCESS);
        }
        return ResultUtil.newFailedResult(StateCode.ERROR);
    }
}