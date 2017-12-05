package org.workholiday.coffeemaker.wrapper;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workholiday.coffeemaker.common.Const;
import org.workholiday.coffeemaker.converter.Converter;
import org.workholiday.coffeemaker.converter.RequestMappingPathConverter;
import org.workholiday.coffeemaker.core.ClassType;
import org.workholiday.coffeemaker.core.FileRole;
import org.workholiday.coffeemaker.core.ImportClass;
import org.workholiday.coffeemaker.core.JavaFileDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        22:09 07/19/2017
 * Version:     1.0
 * Description: 控制器文件封装器
 */
@Data
public class ControllerFileWrapper extends JavaFileWrapper<JavaFileDefinition> implements Const {


    public ControllerFileWrapper(JavaFileDefinition def,
                                 JavaFileWrapper entityWrapper,
                                 JavaFileWrapper serviceWrapper,
                                 JavaFileWrapper voWrapper,
                                 JavaFileWrapper pageWrapper,
                                 JavaFileWrapper pageQueryWrapper) {
        super(def);
        this.entity = entityWrapper;
        this.service = serviceWrapper;
        this.vo = voWrapper;
        this.page = pageWrapper;
        this.pageQuery = pageQueryWrapper;

        Converter<String, String> requestMappingConverter = new RequestMappingPathConverter();
        this.baseMappingPath = requestMappingConverter.convert(def.getClassName());
    }

    private JavaFileWrapper entity;

    private JavaFileWrapper service;

    private JavaFileWrapper vo;

    /**
     * 分页
     */
    private JavaFileWrapper page;
    private JavaFileWrapper pageQuery;

    private String baseMappingPath;



    @Override
    public FileRole getFileRole() {
        return FileRole.CONTROLLER;
    }

    @Override
    public ClassType getClassType() {
        return ClassType.CLASS;
    }

    @Override
    public List<ImportClass> getImportClasses() {

        List<ImportClass> importClasses = new ArrayList<>();

        importClasses.add(new ImportClass(List.class.getName()));
        importClasses.add(new ImportClass("javax.validation.Valid"));

        // 日志类名
        importClasses.add(new ImportClass(Logger.class.getName()));
        importClasses.add(new ImportClass(LoggerFactory.class.getName()));

        // Spring注解类名
        importClasses.add(new ImportClass(SPRING_ANNOTATION_AUTOWIRED));
        importClasses.add(new ImportClass("org.springframework.validation.BindingResult"));
        importClasses.add(new ImportClass(SPRING_ANNOTATION_REST_CONTROLLER));
        importClasses.add(new ImportClass(SPRING_ANNOTATION_REQUEST_MAPPING));
        importClasses.add(new ImportClass(SPRING_ANNOTATION_REQUEST_METHOD));
        importClasses.add(new ImportClass(SPRING_ANNOTATION_REQUEST_BODY));



        // 业务角色类名
        importClasses.add(new ImportClass(entity.getFullQualifiedName()));
        importClasses.add(new ImportClass(service.getFullQualifiedName()));
        importClasses.add(new ImportClass(vo.getFullQualifiedName()));
        importClasses.add(new ImportClass(page.getFullQualifiedName()));
        importClasses.add(new ImportClass(pageQuery.getFullQualifiedName()));

        return importClasses;
    }
}
