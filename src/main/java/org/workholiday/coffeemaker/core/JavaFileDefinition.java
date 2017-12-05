package org.workholiday.coffeemaker.core;

import lombok.Data;

import java.util.List;

/**
 * Function: Java文件定义
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     21:23 06/09/2017
 * Version:  1.0
 */
@Data
public class JavaFileDefinition extends AbstractFileDefinition {


    /**
     * 包名
     */
    private String packageName;

    /**
     * 文件注释
     */
    private JavaFileComment fileComment;

    /**
     * 类名
     */
    private String className;

    /**
     * 类型
     */
    private ClassType classType;

    /**
     * 属性
     */
    private List<FieldDefinition> fieldList;

}
