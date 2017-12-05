package org.workholiday.coffeemaker.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        20:55 07/26/2017
 * Version:     1.0
 * Description: 分页对象
 */
@Data
public class Page<T> extends PageBase implements Serializable {

    /**
     * 总数
     */
    private long total;

    /**
     * 上一页页码
     */
    private int prev;

    /**
     * 下一页页码
     */
    private int next;

    /**
     * 第一页页码
     */
    private int first = 1;

    /**
     * 最后一页页码
     */
    private int last;

    /**
     * 显示的页长
     */
    private int pageLen;

    /**
     * 是否第一页
     */
    private boolean isFirst;

    /**
     * 是否最后一页
     */
    private boolean isLast;

    /**
     * 结果集
     */
    private List<T> result;


    public boolean isFirstPage() {
        return getPageNum() == 1;
    }

    public boolean isLastPage() {
        return (getPageNum() * getPageSize()) - total >= 0;
    }
}
