package org.workholiday.coffeemaker.wrapper;

import lombok.Data;
import org.workholiday.coffeemaker.core.ClassType;
import org.workholiday.coffeemaker.core.FileRole;
import org.workholiday.coffeemaker.core.ImportClass;
import org.workholiday.coffeemaker.core.JavaFileDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * Function: DAO接口文件封装器
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     16:23 06/15/2017
 * Version:  1.0
 */
@Data
public class DaoFileWrapper extends JavaFileWrapper<JavaFileDefinition> {


    public DaoFileWrapper(JavaFileDefinition definition, JavaFileWrapper entityWrapper, JavaFileWrapper pageWrapper, JavaFileWrapper<JavaFileDefinition> pageQueryWrapper) {
        super(definition);
        this.entity = entityWrapper;
        this.page = pageWrapper;
        this.pageQuery = pageQueryWrapper;
    }

    /**
     * 实体
     */
    private JavaFileWrapper entity;

    /**
     * 分页
     */
    private JavaFileWrapper page;
    private JavaFileWrapper pageQuery;

    @Override
    public ClassType getClassType() {
        return ClassType.INTERFACE;
    }

    @Override
    public List<ImportClass> getImportClasses() {

        List<ImportClass> importClasses = new ArrayList<>();
        importClasses.add(new ImportClass(entity.getFullQualifiedName()));
        importClasses.add(new ImportClass(page.getFullQualifiedName()));
        importClasses.add(new ImportClass(pageQuery.getFullQualifiedName()));
        importClasses.add(new ImportClass(List.class.getName()));

        return importClasses;
    }

    @Override
    public FileRole getFileRole() {
        return FileRole.DAO;
    }


}
