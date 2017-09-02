package com.sms.common;

import javax.servlet.http.HttpServletRequest;

/**
 * Author: hbh
 * DateTime: 13-7-18 下午4:32
 * Explain:2014.05.13 原URLUtils.java 合并到此类
 */
public class WebUtils {


    /**
     * 判断是否为Ajax请求
     *
     * @param request 请求
     * @return true of false
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return StringUtils.isNotEmpty(requestType) && requestType.equalsIgnoreCase("XMLHttpRequest");
    }

    /**
     * 获得客户端地址
     *
     * @param request 客户端IP地址载体
     * @return 返回客户端IP
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip;
        if (request == null) {
            return null;
        }
        ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        //由于服务器和客户端均为在同一台机器时，Windows的Hosts文件可能会为localhost指向了IPV6回环地址
        //此时将其转为IPV4形式的回环地址。
        if (ip != null && ip.trim().equals("0:0:0:0:0:0:0:1")) {
            ip = "127.0.0.1";
        }
        return ip;
    }

    /**
     * 从request中获取项目跟地址，以/结尾
     *
     * @param request HttpServletRequest
     * @return url 以/结尾
     */
    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    }

    /**
     * 通过URI和Request获取具体的URL
     *
     * @param request HttpServletRequest
     * @param uri     相对URI地址
     * @return 完整的URL地址
     */
    public static String getURL(HttpServletRequest request, String uri) {
        if (StringUtils.isEmpty(uri)) {
            uri = "";
        } else if (uri.startsWith("/")) {
            uri = uri.substring(1);
        }
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String contextPath = request.getContextPath();
        if (StringUtils.isNotEmpty(contextPath) && !uri.startsWith(contextPath) && !uri.startsWith(contextPath.replaceFirst("/", ""))) {//uri已经包含ContextPath
            url += contextPath;
        }
        return url + "/" + uri;
    }
}
