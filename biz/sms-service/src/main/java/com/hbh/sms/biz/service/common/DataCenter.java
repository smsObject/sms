package com.hbh.sms.biz.service.common;

import com.hbh.sms.model.entity.MeterData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;

import java.text.SimpleDateFormat;

/**
 * Created by user on 2016/12/18.
 */
@lombok.Data
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

    /**
     * 解析 读取仪表数据的返回结果
     *
     * @param hexStr
     * @return
     */
    public static Result<MeterData> parseReadMeterData(String hexStr) {
        //长度判断 17 或 64的长度
        if (hexStr.length() == 132) {
            System.out.println("132:"+hexStr);
            return ResultUtil.newFailedResult(StateCode.ERROR);
        }

        if (hexStr == null || hexStr.trim().length() == 0 || hexStr.length() % 2 != 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "入参错误");
        }
        short[] meterDatas = hexStringToShort(hexStr);
        if (!check(meterDatas)) {
            return ResultUtil.newFailedResult(StateCode.ERROR, "校验失败");
        }
        //解析
        //0x12 0x23 0x56 0x78 0x2C  785623.12吨•
        System.out.println(hexStr);
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
        meterData.setValue(Float.valueOf(stringBuffer.toString()));
        meterData.setValveStatus(Integer.parseInt(status));
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            meterData.setUpLoadTime(dateFormat.parse(upLoadTime));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return ResultUtil.newSuccessResult(meterData);
    }

    /**
     * 设置管理中心号码
     * @param phone1
     * @param phone2
     * @param phone3
     * @return
     */
    public static String getSetManagerCenterCmd(String phone1, String phone2, String phone3) {
        String str11 = phone1.substring(0, 10);
        String str12 = phone1.substring(10, 11) + "EEEEE";

        String str21 = phone2.substring(0, 10);
        String str22 = phone2.substring(10, 11) + "EEEEE";

        String str31 = phone3.substring(0, 10);
        String str32 = phone3.substring(10, 11) + "EEEEE";

        String str0 = "C248";// 42+80 = c2 16*3

        String hexStr = str0 + str11 + str12 + str21 + str22 + str31 + str32;
        return getCrc16(hexStr);
    }

    /**
     * 设置定时点上传
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
     * @param time
     * @return
     */
    public static String getSetActivateCmd(String time) {
        String str0 = "C002";
        int length = time.length();
        String str1 = "";
        for (int i = 0; i<4 - length; i++){
            str1+="0";
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
        System.out.println("00110000012700000000002C0011041D16171EA60F".length());
//        getSetManagerCenterCmd(1,"18205815108");
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