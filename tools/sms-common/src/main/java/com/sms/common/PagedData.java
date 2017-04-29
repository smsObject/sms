package com.sms.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * @author abc
 * @date 2016-08-31
 */
@Data
public class PagedData<T extends Serializable> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 分页大小
     */
    public int pageSize;

    /**
     * 页码
     */
    public int pageNo;

    /**
     * 结果总数
     */
    public long totalSize;

    /**
     * 返回结果集
     */
    public List<T> resultList;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}