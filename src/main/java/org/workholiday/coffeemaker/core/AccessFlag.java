package org.workholiday.coffeemaker.core;

/**
 * Function: 访问权限字典
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     21:33 06/09/2017
 * Version:  1.0
 */
public enum AccessFlag {

    PUBLIC   ("public"),
    PRIVATE  ("private"),
    PROTECTED("protected");



    AccessFlag(String name) {
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
