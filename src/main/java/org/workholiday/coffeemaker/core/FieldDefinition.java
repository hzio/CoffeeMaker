package org.workholiday.coffeemaker.core;

import lombok.Data;
import org.workholiday.coffeemaker.provider.ColumnMetaData;

/**
 * Function: 属性定义
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     21:25 06/09/2017
 * Version:  1.0
 */
@Data
public class FieldDefinition {

    /**
     * 访问权限
     */
    private AccessFlag accessFlag;

    /**
     * 属性类型
     */
    private FieldType fieldType;

    /**
     * 属性名
     */
    private String fieldName;

    /**
     * 注释
     */
    private String comment;

    /**
     * setter名称
     */
    private String setterName;

    /**
     * getter名称
     */
    private String getterName;

    /**
     * 首字母大写的属性名
     */
    private String capitalizedFiledName;

    /**
     * 数据库元数据字段定义
     */
    private ColumnMetaData column;
}
