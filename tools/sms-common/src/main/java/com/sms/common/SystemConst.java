package com.sms.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hbh on 2017/8/28.
 */
public class SystemConst {

    //菜单
    public static final byte MENU_TYPE_MODULE = 1;//模块
    public static final byte MENU_TYPE_FUNCTION = 2;//功能
    public static final byte MENU_TYPE_BUTTON = 3;//按钮

    public static final byte MENU_PARENT_ID_DEFAULT = 0;//默认的顶级菜单父id

    public static final Map<Byte, String> MENU_TYPE_NAME_MAP = new LinkedHashMap<>();

    static {
        MENU_TYPE_NAME_MAP.put(MENU_TYPE_MODULE, "模块");
        MENU_TYPE_NAME_MAP.put(MENU_TYPE_FUNCTION, "功能");
        MENU_TYPE_NAME_MAP.put(MENU_TYPE_BUTTON, "按钮");
    }

    //用户状态
    public static final byte USER_STATUS_ENABLED = 1;//正常
    public static final byte USER_STATUS_DISABLED = 2;//禁用
    public static final Map<Byte, String> USER_STATUS_NAME_MAP = new LinkedHashMap<>();

    public static final String USER_NAME_ADMIN = "admin";

    static {
        USER_STATUS_NAME_MAP.put(USER_STATUS_ENABLED, "启用");
        USER_STATUS_NAME_MAP.put(USER_STATUS_DISABLED, "禁用");
    }


}
