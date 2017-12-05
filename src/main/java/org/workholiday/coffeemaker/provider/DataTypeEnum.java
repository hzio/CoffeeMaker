package org.workholiday.coffeemaker.provider;

import org.workholiday.coffeemaker.core.FieldType;

import java.sql.Types;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        00:32 06/14/2017
 * Version:     1.0
 * Description：数据库、Java类型对应字典
 */
public enum DataTypeEnum {

    // =============================================
    //
    //          字段类型               数据类型编码
    //
    // =============================================
    CHAR	    (FieldType.STRING,     Types.CHAR),
    VARCHAR	    (FieldType.STRING,     Types.VARCHAR),
    LONGVARCHAR	(FieldType.STRING,     Types.LONGVARCHAR),
    NUMERIC	    (FieldType.BIGDECIMAL, Types.NUMERIC),
    DECIMAL	    (FieldType.BIGDECIMAL, Types.DECIMAL),
    BIT	        (FieldType.STRING,     Types.BIT),
    TINYINT	    (FieldType.INTEGER,    Types.TINYINT),
    SMALLINT	(FieldType.INTEGER,    Types.SMALLINT),
    INTEGER	    (FieldType.INTEGER,    Types.INTEGER),
    BIGINT	    (FieldType.LONG,       Types.BIGINT),
    REAL	    (FieldType.STRING,     Types.REAL),
    FLOAT	    (FieldType.FLOAT,      Types.FLOAT),
    DOUBLE	    (FieldType.DOUBLE,     Types.DOUBLE),
    DATE	    (FieldType.DATE,       Types.DATE),
    TIME	    (FieldType.STRING,     Types.TIME),
    TIMESTAMP	(FieldType.DATE,       Types.TIMESTAMP);



    /**
     * 字段类型
     */
    private FieldType fieldType;

    /**
     * 数据类型编码
     */
    private int dataTypeCode;


    DataTypeEnum(FieldType fieldType, int dataTypeCode) {
        this.fieldType = fieldType;
        this.dataTypeCode = dataTypeCode;
    }

    /**
     * 根据编码获取数据类型
     * @param dataTypeCode
     * @return
     */
    public static DataTypeEnum getDataType(int dataTypeCode) {
        for (DataTypeEnum dataTypeEnum : DataTypeEnum.values()) {
            if (dataTypeCode == dataTypeEnum.getDataTypeCode()) {
                return dataTypeEnum;
            }
        }

        return null;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public int getDataTypeCode() {
        return dataTypeCode;
    }

    public void setDataTypeCode(int dataTypeCode) {
        this.dataTypeCode = dataTypeCode;
    }


}
