package com.hbh.sms.biz.service.common;

import com.hbh.sms.model.entity.Concentrator;
import com.hbh.sms.model.entity.Meter;
import com.hbh.sms.model.entity.MeterData;
import com.hbh.sms.model.entity.SmsData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;

import java.text.SimpleDateFormat;

/**
 * Created by user on 2016/12/18.
 */

public class DataCenter {

    /**
     * 读取水表数据指令 数据包含 水表当前读数 ，水表当前读数的单位 ，水表操作故障码 ，阀状态 0x00 0x01 0x06 0x52 0xF0
     */
    public static String READ_METER_CMD = getCrc16("000111"); // 0001079231 读到阀状态  //0001139d31

    /**
     * 开阀门命令 0x86 0x01 0x00 0xB9 0x91
     */
    public static String OPEN_VALVE_CMD = getCrc16("8a0100"); //860100b991

    /**
     * 关闭阀门命令 0x86 0x01 0x01 0x79 0x50
     */
    public static String CLOSE_VALVE_CMD = getCrc16("8a0101"); //8601017950

    public static Concentrator concentrator = new Concentrator();

    public static Result<SmsData> parseSmsData(String hexStr) {

        if (hexStr == null || hexStr.trim().length() == 0 || hexStr.length() % 2 != 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "入参错误");
        }
        short[] smsDatas = hexStringToShort(hexStr);
        if (!check(smsDatas)) {
            return ResultUtil.newFailedResult(StateCode.ERROR, "校验失败");
        }

        if (hexStr.length() == 136) {
            return parseReadSetting(hexStr);
        } else if (hexStr.length() == 42){
            return parseReadMeterData(hexStr);
        }else {
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }
    }

    private static Result<SmsData> parseReadSetting(String hexStr) {
        System.out.println("返回仪表设置:" + hexStr);
        int length = hexStr.length() / 2 - 2;
        String[] strs = new String[length];
        for (int i = 0; i < length; i++) {
            strs[i] = hexStr.substring(i * 2, i * 2 + 2);
        }

        System.out.println("激活延迟时间:"+strs[2]+strs[3]);
        String str0 = strs[2]+strs[3];

        String str1 = strs[4];
        String str2 = strs[5];
        String str3 = strs[6];
        String str4 = strs[7];
        String str5 = strs[8];
        String str6 = strs[9];
        String phone1 = str1 + str2 + str3 + str4 + str5 + str6.substring(0,1);

        String str7 = strs[20];
        String str8 = strs[21];
        String str9 = strs[22];
        String str10 = strs[23];
        String str11 = strs[24];
        String str12 = strs[25];
        String phone2 = str7 + str8 + str9 + str10 + str11 + str12.substring(0,1);

        String str13 = strs[36];
        String str14 = strs[37];
        String str15 = strs[38];
        String str16 = strs[39];
        String str17 = strs[40];
        String str18 = strs[41];
        String phone3 = str13 + str14 + str15 + str16 + str17 + str18.substring(0,1);

        String day1 = strs[52];
        String timing11 = strs[53];
        String timing12 = strs[54];

        String day2 = strs[55];
        String timing21 = strs[56];
        String timing22 = strs[57];

        String day3 = strs[58];
        String timing31 = strs[59];
        String timing32 = strs[60];

        String day4 = strs[61];
        String timing41 = strs[62];
        String timing42 = strs[63];

        Meter meter = new Meter();

        meter.setActivateTime(Integer.parseInt(str0));
        meter.setMc1(phone1);
        meter.setMc2(phone2);
        meter.setMc3(phone3);
        System.out.println("管理中心号码:"+phone1+";"+phone2+";"+phone3);

        meter.setDay1(day1);
        meter.setDay2(day2);
        meter.setDay3(day3);
        meter.setDay4(day4);

        meter.setTiming1(timing11+":"+timing12);
        meter.setTiming2(timing21+":"+timing22);
        meter.setTiming3(timing31+":"+timing32);
        meter.setTiming4(timing41+":"+timing42);

        System.out.println("定时点1:"+meter.getDay1()+"-"+meter.getTiming1());
        System.out.println("定时点2:"+meter.getDay2()+"-"+meter.getTiming2());
        System.out.println("定时点3:"+meter.getDay3()+"-"+meter.getTiming3());
        System.out.println("定时点4:"+meter.getDay4()+"-"+meter.getTiming4());

        SmsData smsData = new SmsData();
        smsData.setMeter(meter);

        return ResultUtil.newSuccessResult(smsData);
    }

    /**
     * 解析 读取仪表数据的返回结果
     *
     * @param hexStr
     * @return
     */
    private static Result<SmsData> parseReadMeterData(String hexStr) {
        System.out.println("返回仪表读数:"+hexStr);

        int length = hexStr.length() / 2 - 2;
        String[] strs = new String[length];
        for (int i = 0; i < length; i++) {
            strs[i] = hexStr.substring(i * 2, i * 2 + 2);
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(strs[2]);
        stringBuffer.append(strs[3]);
        stringBuffer.append(strs[4]);
        stringBuffer.append(strs[5]);
        stringBuffer.append(".");
        stringBuffer.append(strs[6]);
        stringBuffer.append(strs[7]);

        String error = strs[11];
        String status = strs[12];
        String year = strs[13];
        String month = strs[14];
        String day = strs[15];
        String hour = strs[16];
        String minute = strs[17];
        String second = strs[18];

        System.out.println(stringBuffer.toString());
        System.out.println(error);
        System.out.println(status);

        String upLoadTime = "20" + year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
        System.out.println(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);
        MeterData meterData = new MeterData();
        SmsData smsData = new SmsData();
        meterData.setValue(Float.valueOf(stringBuffer.toString()));
        meterData.setValveStatus(Integer.parseInt(status));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            meterData.setUpLoadTime(dateFormat.parse(upLoadTime));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        smsData.setMeterData(meterData);
        return ResultUtil.newSuccessResult(smsData);
    }

    /**
     * 设置管理中心号码
     *
     * @param phone1
     * @param phone2
     * @param phone3
     * @return
     */
    public static String getSetManagerCenterCmd(String phone1, String phone2, String phone3) {
        String str11 = phone1.substring(0, 10);
        String str12 = phone1.substring(10, 11) + "EEEEEEEEEEEEEEEEEEEEE";

        String str21 = phone2.substring(0, 10);
        String str22 = phone2.substring(10, 11) + "EEEEEEEEEEEEEEEEEEEEE";

        String str31 = phone3.substring(0, 10);
        String str32 = phone3.substring(10, 11) + "EEEEEEEEEEEEEEEEEEEEE";

        String str0 = "C248";// 42+80 = c2 16*3

        String hexStr = str0 + str11 + str12 + str21 + str22 + str31 + str32;
        return getCrc16(hexStr);
    }

    /**
     * 设置定时点上传
     *
     * @param cmd1
     * @param cmd2
     * @param cmd3
     * @param cmd4
     * @return
     */
    public static String getSetTimingCmd(String cmd1, String cmd2, String cmd3, String cmd4) {
        String str0 = "E312";
        String hexStr = str0 + cmd1 + cmd2 + cmd3 + cmd4;
        return getCrc16(hexStr);
    }

    /**
     * 设置激活时间
     *
     * @param time
     * @return
     */
    public static String getSetActivateCmd(String time) {
        String str0 = "C002";
        int length = time.length();
        String str1 = "";
        for (int i = 0; i < 4 - length; i++) {
            str1 += "0";
        }
        String hexStr = str0 + str1 + time;
        return getCrc16(hexStr);
    }

    /**
     * 解析阀状态值
     *
     * @param hexStr
     * @return
     */
    public static Result<MeterData> parseValveStatus(String hexStr) {
        if (hexStr == null || hexStr.trim().length() == 0 || hexStr.length() % 2 != 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "入参错误");
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("4040360015372508177EEEEEEEEEEEEEEEEEEEEE15372508177EEEEEEEEEEEEEEEEEEEEE15032131012131032131052131381FEFE72037082320152320212320AA00E3F5".length());
//        System.out.println(getCrc16("122356782C"));
    }

    public static String getCrc16(String hexStr) {
        int length = hexStr.length() / 2;
        short[] strs = new short[length + 2];
        for (int i = 0; i < length; i++) {
            strs[i] = Short.parseShort(hexStr.substring(i * 2, i * 2 + 2), 16);
        }
        crc16(strs);
        return shortToHexString(strs);
    }

    private static boolean check(short[] datas) {
        int length = datas.length;
        short[] checks = new short[2];
        checks[0] = datas[length - 2];
        checks[1] = datas[length - 1];
        crc16(datas);//最后两位被替换
        if (datas[length - 2] == checks[0] && datas[length - 1] == checks[1]) {
            return true;
        }
        return false;
    }

    private static void crc16(short[] sends) {
        char temp = 0xffff;
        int i, j;
        int size = sends.length - 2; //传进来的真正有效位
        for (j = 0; j < size; j++) {
            temp ^= sends[j];
            for (i = 0; i < 8; i++) {
                if ((temp & 0x01) > 0) {
                    temp >>= 1;
                    temp ^= 0xA001;
                } else {
                    temp >>= 1;
                }
            }
        }
        sends[size] = (short) ((temp & 0xFF00) >> 8);
        sends[size + 1] = (short) (temp & 0x00FF);
    }

    private static String shortToHexString(short[] sends) {
        StringBuffer stringBuffer = new StringBuffer();
        for (short i : sends) {
            if (i < 10) {
                stringBuffer.append("0" + Integer.toHexString(i));
            } else {
                stringBuffer.append(Integer.toHexString(i));
            }
        }
        return stringBuffer.toString();
    }

    private static short[] hexStringToShort(String hexStr) {
        int length = hexStr.length() / 2;
        short[] meterDatas = new short[length];
        for (int i = 0; i < length; i++) {
            meterDatas[i] = Short.parseShort(hexStr.substring(i * 2, i * 2 + 2), 16);
        }
        return meterDatas;
    }
}