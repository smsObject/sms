package com.hbh.sms.biz.service.common;

import com.hbh.sms.model.entity.MeterData;
import com.sms.common.Result;
import com.sms.common.ResultUtil;
import com.sms.common.StateCode;

/**
 * Created by user on 2016/12/18.
 */
@lombok.Data
public class Data {

    /**
     * 读取水表数据指令 数据包含 水表当前读数 ，水表当前读数的单位 ，水表操作故障码 ，阀状态 0x00 0x01 0x06 0x52 0xF0
     */
    public static String READ_METER_CMD = "0001079231";

    /**
     * 关闭阀门命令 0x86 0x01 0x01 0x79 0x50
     */
    public static String CLOSE_VALVE_CMD = "8601017950";

    /**
     * 开阀门命令 0x86 0x01 0x00 0xB9 0x91
     */
    public static String OPEN_VALVE_CMD = "860100b991";

    /**
     * 解析 读取仪表数据的返回结果
     *
     * @param hexStr
     * @return
     */
    public static Result<MeterData> parseReadMeterData(String hexStr) {
        if (hexStr == null || hexStr.trim().length() == 0 || hexStr.length() % 2 != 0) {
            return ResultUtil.newFailedResult(StateCode.PARAMETERS_FAILED, "入参错误");
        }
        short[] meterDatas = hexStringToShort(hexStr);
        if (! check(meterDatas)){
            return ResultUtil.newFailedResult(StateCode.ERROR, "校验失败");
        }


        return null;
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

    private static boolean check(short[] datas) {
        int length = datas.length;
        short[] checks = new short[2];
        checks[0] = datas[length - 2];
        checks[1] = datas[length - 1];
        crc16(datas, length - 2);//最后两位被替换
        if (datas[length - 2] == checks[0] && datas[length - 1] == checks[1]) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Data.parseReadMeterData("0006000100002C000B07");
        int a = 5;
        short b = 0x2C;
        System.out.println(a % 2);
        String hexStr = "0006000100002C000B07";
        int length = hexStr.length() / 2;
        short[] meterDatas = new short[length];
        for (int i = 0; i < length; i++) {
            meterDatas[i] = Short.parseShort(hexStr.substring(i * 2, i * 2 + 2), 16);
        }
        check(meterDatas);
    }

    private static void crc16(short[] sends, int size) {
        char temp = 0xffff;
        int i, j;
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