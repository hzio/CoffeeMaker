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
 * Date:        00:22 07/27/2017
 * Version:     1.0
 * Description: 分页对象文件封装器
 */
@Data
public class PageFileWrapper extends JavaFileWrapper<JavaFileDefinition> implements Const {

    public PageFileWrapper(JavaFileDefinition def, JavaFileWrapper pageBaseFileWrapper, String packageName) {
        super(def, packageName);
        this.pageBase = pageBaseFileWrapper;
    }

    private JavaFileWrapper pageBase;

    @Override
    public FileRole getFileRole() {
        return FileRole.PAGE;
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
