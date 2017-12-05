package org.workholiday.coffeemaker.wrapper;

import lombok.Data;
import org.workholiday.coffeemaker.common.Const;
import org.workholiday.coffeemaker.core.*;
import org.workholiday.coffeemaker.provider.DataTypeEnum;
import org.workholiday.coffeemaker.util.ObjectUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Function: entity文件封装器
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     14:29 06/15/2017
 * Version:  1.0
 */
@Data
public class EntityFileWrapper extends JavaFileWrapper<JavaFileDefinition> implements Const {

    /** Java语言默认包 */
    private static final String LANG_PACKAGE_PREFIX = "java.lang.";


    /** 主键 */
    private FieldDefinition pk;

    public EntityFileWrapper(JavaFileDefinition entityDefinition) {
        super(entityDefinition);
        Objects.requireNonNull(entityDefinition);
        // 初始化主键
        initPK(entityDefinition);
    }

    /**
     * 初始化主键
     * @param def
     */
    private void initPK(JavaFileDefinition def) {
        if (ObjectUtil.isNotBlank(def.getFieldList())) {
            for (FieldDefinition field : def.getFieldList()) {
                if (field.getColumn().isPrimaryKey()) {
                    this.pk = field;
                }
            }
        }
    }


    @Override
    public ClassType getClassType() {
        return ClassType.CLASS;
    }

    /**
     * 初始化引入类列表
     * @return
     */
    @Override
    public List<ImportClass> getImportClasses() {

        List<ImportClass> importClasses = new ArrayList<>();
        importClasses.add(new ImportClass(Serializable.class.getName()));
        importClasses.add(new ImportClass(LOMBOK_ANNOTATION_DATA));

        for (FieldDefinition field : getDef().getFieldList()) {
            DataTypeEnum dataType = DataTypeEnum.getDataType(field.getColumn().getDataType());
            // 如果是非java.lang包下的类则需要显式引入
            if (dataType != null && !dataType.getFieldType().getFullQualifiedName().startsWith(LANG_PACKAGE_PREFIX)) {
                ImportClass clazz = new ImportClass(dataType.getFieldType().getFullQualifiedName());

                if (!importClasses.contains(clazz)) {
                    importClasses.add(clazz);
                }
            }
        }
        return importClasses;
    }

    @Override
    public FileRole getFileRole() {
        return FileRole.ENTITY;
    }


}
