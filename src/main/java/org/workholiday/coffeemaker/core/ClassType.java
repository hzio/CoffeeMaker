package org.workholiday.coffeemaker.core;

/**
 * Function: 类类型字典
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     21:24 06/09/2017
 * Version:  1.0
 */
public enum ClassType {

    CLASS    ("class"),
    INTERFACE("interface"),
    ENUM     ("enum");


    ClassType(String name) {
        this.name = name;
    }

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
