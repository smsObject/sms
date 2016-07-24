package com.sms.common;

/**
 * Created by hbh on 2016/7/24.
 */
public class Page {
    private static final Integer DEFAULT_PAGENO=1;
    private static final Integer DEFAULT_PAGESIZE=20;
    private Integer pageNo    =  DEFAULT_PAGENO;
    private Integer pageSize  =  DEFAULT_PAGESIZE;
    private Integer totalCount ;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }
}