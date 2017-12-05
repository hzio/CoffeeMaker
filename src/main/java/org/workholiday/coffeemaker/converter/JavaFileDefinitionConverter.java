package org.workholiday.coffeemaker.converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workholiday.coffeemaker.common.Configuration;
import org.workholiday.coffeemaker.core.AccessFlag;
import org.workholiday.coffeemaker.core.ClassType;
import org.workholiday.coffeemaker.core.FieldDefinition;
import org.workholiday.coffeemaker.core.JavaFileDefinition;
import org.workholiday.coffeemaker.provider.ColumnMetaData;
import org.workholiday.coffeemaker.provider.DataTypeEnum;
import org.workholiday.coffeemaker.provider.TableMetaData;
import org.workholiday.coffeemaker.util.ObjectUtil;
import org.workholiday.coffeemaker.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Function: Java文件定义转换器
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     15:06 06/14/2017
 * Version:  1.0
 */
public class JavaFileDefinitionConverter implements FileDefinitionConverter<JavaFileDefinition, TableMetaData> {

    private static Logger logger = LoggerFactory.getLogger(JavaFileDefinitionConverter.class);

    private Configuration configuration;


    public JavaFileDefinitionConverter(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 把一个数据表元数据转换为一个Java定义描述文件
     * @param table
     * @return
     */
    @Override
    public JavaFileDefinition convert(TableMetaData table) {

        JavaFileDefinition def = new JavaFileDefinition();
        // 组装文件定义
        Converter<String, String> tableNameConverter = new ClassNameConverter(configuration.getTablePrefix());
        String className = tableNameConverter.convert(table.getTableName());
        def.setClassName(className);
        def.setClassType(ClassType.CLASS);

        if (ObjectUtil.isNotBlank(table.getColumnList())) {

            List<FieldDefinition> fieldList = new ArrayList<>();
            // 属性名转换器
            Converter<String, String> filedNameConverter = new FiledNameConverter();

            for (ColumnMetaData column : table.getColumnList()) {
                // 组装属性
                FieldDefinition field = new FieldDefinition();
                // 属性访问权限
                field.setAccessFlag(AccessFlag.PRIVATE);
                DataTypeEnum dataTypeEnum = DataTypeEnum.getDataType(column.getDataType());
                Objects.requireNonNull(dataTypeEnum);
                // 数据类型
                field.setFieldType(dataTypeEnum.getFieldType());
                // 属性名
                field.setFieldName(filedNameConverter.convert(column.getColumnName()));
                // 注释
                field.setComment(column.getRemarks());
                // setter & getter
                field.setSetterName("set" + StringUtil.capitalize(field.getFieldName()));
                field.setGetterName("get" + StringUtil.capitalize(field.getFieldName()));

                field.setCapitalizedFiledName(StringUtil.capitalize(field.getFieldName()));

                // 元数据
                field.setColumn(column);

                fieldList.add(field);
            }

            def.setFieldList(fieldList);
        }

        return def;
    }

}
