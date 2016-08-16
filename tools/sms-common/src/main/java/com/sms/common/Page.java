package com.sms.common;

import java.io.Serializable;

/**
 * Created by hbh on 2016/7/24.
 */
public class Page implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Integer DEFAULT_OFFSET=0;
    private static final Integer DEFAULT_PAGESIZE=20;
    private Integer offset = DEFAULT_OFFSET;
    private Integer limit =DEFAULT_PAGESIZE;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}