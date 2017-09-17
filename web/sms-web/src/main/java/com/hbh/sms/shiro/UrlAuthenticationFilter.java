package com.hbh.sms.shiro;

import com.sms.common.WebUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hbh on 2017/8/30.
 */
public class UrlAuthenticationFilter extends AccessControlFilter {
    private static final String NAME = "URL权限认证过滤器:";

    /**
     * 表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        String uri = ((HttpServletRequest) request).getServletPath();
        Subject subject = SecurityUtils.getSubject();
        String logHead = NAME + "[" + subject.getPrincipal() + "][" + uri + "] -> ";
        //.replaceAll("/", ":")
        String permissionStr = uri.replaceFirst("/sms/","");
        boolean allowed = subject.isPermitted(permissionStr);
//        logger.info(logHead + (allowed ? "认证成功" : "认证失败"));
        return allowed;
    }

    /**
     * 表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = ((HttpServletRequest) request).getServletPath();
        String msg = "Permission denied [ " + uri + " ]";
        if (WebUtils.isAjaxRequest(httpServletRequest)) {
            httpServletResponse.setHeader("_filterStatus", "no_permission");
            httpServletResponse.setHeader("_filterMsg", msg);
        }
        response.getWriter().write(msg);
        response.getWriter().close();
        return false;
    }
}
