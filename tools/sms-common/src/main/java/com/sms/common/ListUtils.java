package com.sms.common;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.*;

/**
 * Created by Tomtao on 2017/7/30.
 */
public class ListUtils extends CollectionUtils {

    public static List<String> toList(String strings, String separator) {
        if (StringUtils.isEmpty(strings)) {
            return null;
        }
        if (separator == null) {
            separator = "";
        }
        List<String> list = new LinkedList<>();
        Collections.addAll(list, strings.split(separator));
        return list;
    }

    public static List<Integer> toIntList(String strings, String separator) {
        List<String> strList = toList(strings, separator);
        if (isEmpty(strList)) {
            return null;
        }
        List<Integer> intList = new LinkedList<>();
        for (String str : strList) {
            intList.add(Integer.valueOf(str.trim()));
        }
        return intList;
    }

    public static String join(Collection collection, String separator) {
        if (isEmpty(collection)) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        boolean first = true;
        if (separator == null) {
            separator = "";
        }
        for (Object value : collection) {
            if (first) {
                first = false;
            } else {
                buffer.append(separator);
            }
            buffer.append(value.toString());
        }
        return buffer.toString();
    }

    public static String join(Object[] objects, String separator) {
        return join(Arrays.asList(objects), separator);
    }

    public static void main(String[] args) {
        String strs = "abc,dfx,ded";
        List<String> list = toList(strs, ",");

        System.out.println(Arrays.deepToString(list.toArray()));
    }
}
