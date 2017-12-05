package org.workholiday.coffeemaker.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        21:52 07/26/2017
 * Version:     1.0
 * Description: Page基类
 */
@Data
public abstract class PageBase implements Serializable {

    /**
     * 页号
     */
    private int pageNum = 1;

    /**
     * 页长
     */
    private int pageSize = 10;

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum < 0 ? 1 : pageNum;
    }
}
