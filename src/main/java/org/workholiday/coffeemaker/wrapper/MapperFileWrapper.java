package org.workholiday.coffeemaker.wrapper;

import lombok.Data;
import org.workholiday.coffeemaker.core.FileRole;
import org.workholiday.coffeemaker.core.JavaFileDefinition;
import org.workholiday.coffeemaker.core.MybatisMapperFileDefinition;
import org.workholiday.coffeemaker.provider.TableMetaData;

/**
 * Function: mapper文件封装器
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     14:58 06/15/2017
 * Version:  1.0
 */
@Data
public class MapperFileWrapper<T extends MybatisMapperFileDefinition> extends AbstractFileWrapper<T> {


    public MapperFileWrapper(T definition,
                             JavaFileWrapper<JavaFileDefinition> entityWrapper,
                             JavaFileWrapper<JavaFileDefinition> daoWrapper,
                             JavaFileWrapper<JavaFileDefinition> pageQueryWrapper,
                             TableMetaData table) {
        super(definition);
        super.setFileName(((JavaFileDefinition)entityWrapper.getDef()).getClassName() + this.getFileRole().getRoleName());
        this.entity = entityWrapper;
        this.dao = daoWrapper;
        this.pageQuery = pageQueryWrapper;
        this.table = table;

        super.setOutputPath(entityWrapper.getOutputPath());
    }

    private JavaFileWrapper<JavaFileDefinition> entity;

    private JavaFileWrapper<JavaFileDefinition> dao;

    private JavaFileWrapper<JavaFileDefinition> pageQuery;

    private TableMetaData table;

    @Override
    public FileRole getFileRole() {
        return FileRole.MAPPER;
    }
}
