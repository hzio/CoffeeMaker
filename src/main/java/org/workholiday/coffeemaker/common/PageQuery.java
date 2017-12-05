package org.workholiday.coffeemaker.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        21:52 07/26/2017
 * Version:     1.0
 * Description: 分页查询器
 */
@Data
public class PageQuery<T> extends PageBase implements Serializable {

    /**
     * 查询条件
     */
    private T query;

    /**
     * 偏移量
     */
    private long offset;

    public long getOffset() {
        return (getPageNum() - 1) * getPageSize();
    }
//    /**
//     * 排序字段
//     */
//    private List<String> orderBy;

//    /**
//     * 排序
//     */
//    private SortEnum sortBy;

}
