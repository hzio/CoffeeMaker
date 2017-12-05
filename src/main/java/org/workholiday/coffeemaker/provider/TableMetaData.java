package org.workholiday.coffeemaker.provider;

import lombok.Data;

import java.util.List;

/**
 * Function: 表信息元数据
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     00:14 06/14/2017
 * Version:  1.0
 */
@Data
public class TableMetaData {

    /**
     * 表目录（可能为空）
     */
    private String tableCat;

    /**
     * 表的架构（可能为空）
     */
    private String tableSchemaName;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 字段列表
     */
    private List<ColumnMetaData> columnList;

    /**
     * 主键
     */
    private ColumnMetaData pk;
}
