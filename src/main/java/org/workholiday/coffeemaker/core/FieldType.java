package org.workholiday.coffeemaker.core;


/**
 * Function: 属性类型字典
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     21:26 06/09/2017
 * Version:  1.0
 */
public enum FieldType {

    // =======================================================================
    //
    //        原始类型            封装类型         全限定名
    //
    // =======================================================================

    STRING    ("String",         "String",     "java.lang.String"     ),
    INTEGER   ("int",            "Integer",    "java.lang.Integer"    ),
    LONG      ("long",           "Long",       "java.lang.Long"       ),
    DOUBLE    ("double",         "Double",     "java.lang.Double"     ),
    FLOAT     ("float",          "Float",      "java.lang.Float"      ),
    SHORT     ("short",          "Short",      "java.lang.Short"      ),
    BYTE      ("byte",           "Byte",       "java.lang.Byte"       ),
    BIGDECIMAL("double",         "BigDecimal", "java.math.BigDecimal" ),
    DATE      ("Date",           "Date",       "java.util.Date"       );


    FieldType(String primitiveName, String boxingName, String fullQualifiedName) {
        this.primitiveName = primitiveName;
        this.boxingName = boxingName;
        this.fullQualifiedName = fullQualifiedName;
    }

    /**
     * 原始类型
     */
    private String primitiveName;


    /**
     * 封装类型
     */
    private String boxingName;

    /**
     * 全限定名
     */
    private String fullQualifiedName;

    public String getPrimitiveName() {
        return primitiveName;
    }

    public void setPrimitiveName(String primitiveName) {
        this.primitiveName = primitiveName;
    }

    public String getBoxingName() {
        return boxingName;
    }

    public void setBoxingName(String boxingName) {
        this.boxingName = boxingName;
    }

    public String getFullQualifiedName() {
        return fullQualifiedName;
    }

    public void setFullQualifiedName(String fullQualifiedName) {
        this.fullQualifiedName = fullQualifiedName;
    }
}
