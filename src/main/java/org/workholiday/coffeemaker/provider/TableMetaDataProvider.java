package org.workholiday.coffeemaker.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workholiday.coffeemaker.util.JdbcUtil;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Function: 表信息元数据提供者
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     11:42 06/14/2017
 * Version:  1.0
 */
public class TableMetaDataProvider {

    private static Logger logger = LoggerFactory.getLogger(TableMetaDataProvider.class);

    /**
     * 获取表元数据
     * @param tableName
     * @return
     */
    public TableMetaData fetchMetaData(String tableName) {

        Objects.requireNonNull(tableName);

        Connection conn = null;
        TableMetaData tableMetaData = null;

        try {
            conn = JdbcUtil.getConnection();
            DatabaseMetaData metaData = conn.getMetaData();

            // 获取表信息
            ResultSet tableRs = metaData.getTables(conn.getCatalog(), "%", tableName, new String[]{"TABLE"});
            // 获取表主键
            ResultSet pkRs = metaData.getPrimaryKeys(conn.getCatalog(), "%", tableName);
            String pkName = null;
            while (pkRs.next()) {
                pkName = pkRs.getString(4);
                break;
            }

            while (tableRs.next()) {
                String metaTableName = tableRs.getString("TABLE_NAME");
                String remark = tableRs.getString("REMARKS");

                // 找到匹配的表名
                if (tableName.equals(metaTableName)) {

                    ResultSet columnRs = metaData.getColumns(conn.getCatalog(),"%", tableName,"%");

                    tableMetaData = new TableMetaData();
                    tableMetaData.setTableCat(conn.getCatalog());
                    tableMetaData.setTableSchemaName(conn.getSchema());
                    tableMetaData.setTableName(tableName);

                    List<ColumnMetaData> columnList = new ArrayList<>();

                    while(columnRs.next()) {
                        ColumnMetaData column = new ColumnMetaData();
                        // 获取字段元数据
                        String tableCat         = columnRs.getString("TABLE_CAT");
                        String tableSchemaName  = columnRs.getString("TABLE_SCHEM");
                        String tbName           = columnRs.getString("TABLE_NAME");
                        String columnName       = columnRs.getString("COLUMN_NAME");
                        int dataType            = columnRs.getInt("DATA_TYPE");
                        String dataTypeName     = columnRs.getString("TYPE_NAME");
                        int columnSize          = columnRs.getInt("COLUMN_SIZE");
                        int decimalDigits       = columnRs.getInt("DECIMAL_DIGITS");
                        int nullable            = columnRs.getInt("NULLABLE");
                        String remarks          = columnRs.getString("REMARKS");
                        String columnDef        = columnRs.getString("COLUMN_DEF");
                        int sqlDataType         = columnRs.getInt("SQL_DATA_TYPE");
                        int charOctetLength     = columnRs.getInt("CHAR_OCTET_LENGTH");
                        int ordinalPosition     = columnRs.getInt("ORDINAL_POSITION");
                        String isNullable       = columnRs.getString("IS_NULLABLE");
                        String isAutoincrement  = columnRs.getString("IS_AUTOINCREMENT");

                        //logger.info("columnName:{}, dataType:{}, dataTypeName:{}", columnName, dataType, dataTypeName);
                        // 组装对象
                        column.setColumnName(columnName);
                        column.setDataType(dataType);
                        column.setDataTypeName(dataTypeName);

                        DataTypeEnum dataTypeEnum = DataTypeEnum.getDataType(dataType);
                        Objects.requireNonNull(dataTypeEnum);
                        column.setJdbcTypeName(dataTypeEnum.name());
                        column.setColumnSize(columnSize);
                        column.setDecimalDigits(decimalDigits);
                        column.setNullable(nullable);
                        column.setRemarks(remarks);
                        column.setColumnDef(columnDef);
                        column.setSqlDataType(sqlDataType);
                        column.setCharOctetLength(charOctetLength);
                        column.setOrdinalPosition(ordinalPosition);
                        column.setIsNullable(isNullable);
                        column.setIsAutoincrement(isAutoincrement);
                        column.setPrimaryKey(columnName.equals(pkName));

                        columnList.add(column);

                        // 如果该字段是主键
                        if (column.isPrimaryKey()) {
                            tableMetaData.setPk(column);
                        }
                    }

                    tableMetaData.setColumnList(columnList);
                    break;
                }

            }

        }
        catch (SQLException e) {
            logger.error("获取表元数据错误", e);
        }
        finally {
            // 关闭数据连接
            JdbcUtil.close(conn);
        }

        return tableMetaData;
    }


}
