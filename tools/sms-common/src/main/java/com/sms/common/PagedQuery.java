package com.sms.common;

import java.io.Serializable;

/**
 * Created by user on 2016/11/13.
 */
public class PagedQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int defaultPageSize = 20;
    public static final int defaultPageNo = 1;

    /**
     * 分页大小
     */
    private Integer pageSize = defaultPageSize;

    /**
     * 页码
     */
    private Integer pageNo = defaultPageNo;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize == null || defaultPageSize <= 0 ? defaultPageSize : pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo == null || pageNo <= 0 ? defaultPageNo : pageNo;
    }

    public Integer getStartRow() {
        return (pageNo - 1) * pageSize;
    }
//
//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
//    }
}
