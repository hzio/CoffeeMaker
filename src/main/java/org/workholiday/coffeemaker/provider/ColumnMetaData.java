package org.workholiday.coffeemaker.provider;

import lombok.Data;

/**
 * Function: 字段信息元数据
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     15:59 06/14/2017
 * Version:  1.0
 */
@Data
public class ColumnMetaData {

    /**
     * 列名
     */
    private String columnName;

    /**
     * 数据类型
     * @see java.sql.Types
     */
    private int dataType;

    /**
     * 数据类型名称
     */
    private String dataTypeName;

    /**
     * JDBC数据类型名称
     */
    private String jdbcTypeName;

    /**
     * 列大小
     */
    private int columnSize;

    /**
     * 小数位数
     */
    private int decimalDigits;

    /**
     * 基数（通常是10或2）
     */
    private int numPrecRadix;

    /**
     * 是否允许为null
     */
    private int nullable;

    /**
     * 列描述
     */
    private String remarks;

    /**
     * 默认值
     */
    private String columnDef;

    /**
     * SQL数据类型
     */
    private int sqlDataType;

    /**
     * char类型的列中的最大字节数
     */
    private int charOctetLength;

    /**
     * 表中列的索引（从1开始）
     */
    private int ordinalPosition;

    /**
     * 是否允许为空
     */
    private String isNullable;

    /**
     * 列是否是自动递增
     */
    private String isAutoincrement;

    /**
     * 是否主键
     */
    private boolean primaryKey;

}
