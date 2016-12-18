package com.hbh.sms.biz.service.common;

import com.hbh.sms.model.entity.MeterData;

/**
 * Created by user on 2016/12/18.
 */
@lombok.Data
public class Data {

    /**
     * 读取水表数据指令 数据包含 水表当前读数 ，水表当前读数的单位 ，水表操作故障码 ，阀状态 0x00 0x01 0x06 0x52 0xF0
     */
    public  static String READ_METER_CMD = "0001079231";

    /**
     * 关闭阀门命令 0x86 0x01 0x01 0x79 0x50
     */
    public static String CLOSE_VALVE_CMD = "8601017950";

    /**
     * 开阀门命令 0x86 0x01 0x00 0xB9 0x91
     */
    public static String OPEN_VALVE_CMD ="860100b991";

    /**
     * 解析 读取仪表数据的返回结果
     * @param hexStr
     * @return
     */
    public static MeterData parseReadMeterData(String hexStr){
        return  null;
    }

    /**
     * 解析阀状态值
     * @param hexStr
     * @return
     */
    public static MeterData parseValveStatus(String hexStr){
    return null;
    }
}