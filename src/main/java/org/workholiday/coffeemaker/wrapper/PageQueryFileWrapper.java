package org.workholiday.coffeemaker.wrapper;

import lombok.Data;
import org.workholiday.coffeemaker.common.Const;
import org.workholiday.coffeemaker.core.ClassType;
import org.workholiday.coffeemaker.core.FileRole;
import org.workholiday.coffeemaker.core.ImportClass;
import org.workholiday.coffeemaker.core.JavaFileDefinition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        00:23 07/27/2017
 * Version:     1.0
 * Description: 分页查询器封装器
 */
@Data
public class PageQueryFileWrapper extends JavaFileWrapper<JavaFileDefinition> implements Const {


    public PageQueryFileWrapper(JavaFileDefinition def, JavaFileWrapper pageBaseFileWrapper, String packageName) {
        super(def, packageName);
        this.pageBase = pageBaseFileWrapper;
    }

    private JavaFileWrapper pageBase;

    @Override
    public FileRole getFileRole() {
        return FileRole.PAGE_QUERY;
    }

    @Override
    public ClassType getClassType() {
        return ClassType.CLASS;
    }

    @Override
    public List<ImportClass> getImportClasses() {

        List<ImportClass> importClasses = new ArrayList<>();

        importClasses.add(new ImportClass(LOMBOK_ANNOTATION_DATA));
        importClasses.add(new ImportClass(List.class.getName()));
        importClasses.add(new ImportClass(Serializable.class.getName()));
        importClasses.add(new ImportClass(pageBase.getFullQualifiedName()));

        return importClasses;

    }



}
