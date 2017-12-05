package org.workholiday.coffeemaker.wrapper;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workholiday.coffeemaker.common.Const;
import org.workholiday.coffeemaker.core.ClassType;
import org.workholiday.coffeemaker.core.FileRole;
import org.workholiday.coffeemaker.core.ImportClass;
import org.workholiday.coffeemaker.core.JavaFileDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        16:23 06/15/2017
 * Version:     1.0
 * Description: Service实现文件封装器
 */
@Data
public class ServiceImplFileWrapper extends JavaFileWrapper<JavaFileDefinition> implements Const {


    public ServiceImplFileWrapper(JavaFileDefinition definition,
                                  JavaFileWrapper entityWrapper,
                                  JavaFileWrapper daoWrapper,
                                  JavaFileWrapper serviceWrapper,
                                  JavaFileWrapper pageWrapper,
                                  JavaFileWrapper<JavaFileDefinition> pageQueryWrapper) {
        super(definition);
        this.init(entityWrapper, daoWrapper, serviceWrapper, pageWrapper, pageQueryWrapper);
    }

    /**
     * 初始化
     * @param entityWrapper
     * @param daoWrapper
     */
    private void init(JavaFileWrapper entityWrapper, JavaFileWrapper daoWrapper,
                      JavaFileWrapper serviceWrapper, JavaFileWrapper pageWrapper,
                      JavaFileWrapper<JavaFileDefinition> pageQueryWrapper) {
        this.entity = entityWrapper;
        this.dao = daoWrapper;
        this.service = serviceWrapper;
        this.page = pageWrapper;
        this.pageQuery = pageQueryWrapper;
    }


    private JavaFileWrapper entity;

    private JavaFileWrapper dao;

    private JavaFileWrapper service;

    /**
     * 分页
     */
    private JavaFileWrapper page;
    private JavaFileWrapper pageQuery;

    @Override
    public ClassType getClassType() {
        return ClassType.CLASS;
    }

    @Override
    public List<ImportClass> getImportClasses() {
        List<ImportClass> importClasses = new ArrayList<>();

        importClasses.add(new ImportClass(List.class.getName()));
        importClasses.add(new ImportClass(Objects.class.getName()));

        // 日志类名
        importClasses.add(new ImportClass(Logger.class.getName()));
        importClasses.add(new ImportClass(LoggerFactory.class.getName()));

        // Spring注解类名
        importClasses.add(new ImportClass(SPRING_ANNOTATION_SERVICE));
        importClasses.add(new ImportClass(SPRING_ANNOTATION_AUTOWIRED));

        // 业务角色类名
        importClasses.add(new ImportClass(entity.getFullQualifiedName()));
        importClasses.add(new ImportClass(service.getFullQualifiedName()));
        importClasses.add(new ImportClass(dao.getFullQualifiedName()));

        importClasses.add(new ImportClass(page.getFullQualifiedName()));
        importClasses.add(new ImportClass(pageQuery.getFullQualifiedName()));

        return importClasses;
    }

    @Override
    public FileRole getFileRole() {
        return FileRole.SERVICE_IMPL;
    }


}
