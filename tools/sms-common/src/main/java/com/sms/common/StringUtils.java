package com.sms.common;

/**
 * User: hbh
 * Date: 2017/3/3
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String emptyIfBlank(String str) {
        return defaultIfBlank(str, "");
    }

    public static boolean notEquals(final CharSequence str1, final CharSequence str2) {
        return !equals(str1, str2);
    }

    public static boolean notEqualsIgnoreCase(final CharSequence str1, final CharSequence str2) {
        return !equalsIgnoreCase(str1, str2);
    }
}
