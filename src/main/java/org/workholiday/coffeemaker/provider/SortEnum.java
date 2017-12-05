package org.workholiday.coffeemaker.provider;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        21:59 07/26/2017
 * Version:     1.0
 * Description: 排序
 */
public enum  SortEnum {

    ASC (1, "asc"),
    DESC(2, "desc");

    /**
     * 编码
     */
    private int code;

    /**
     * 排序
     */
    private String sort;


    SortEnum(int code, String sort) {
        this.code = code;
        this.sort = sort;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
