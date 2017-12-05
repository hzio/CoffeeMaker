package org.workholiday.coffeemaker;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workholiday.coffeemaker.common.Configuration;
import org.workholiday.coffeemaker.converter.FileDefinitionConverter;
import org.workholiday.coffeemaker.converter.JavaFileDefinitionConverter;
import org.workholiday.coffeemaker.core.JavaFileComment;
import org.workholiday.coffeemaker.core.JavaFileDefinition;
import org.workholiday.coffeemaker.core.MybatisMapperFileDefinition;
import org.workholiday.coffeemaker.parser.DefaultFileParser;
import org.workholiday.coffeemaker.parser.FileParser;
import org.workholiday.coffeemaker.provider.TableMetaData;
import org.workholiday.coffeemaker.provider.TableMetaDataProvider;
import org.workholiday.coffeemaker.util.DateUtil;
import org.workholiday.coffeemaker.wrapper.*;
import org.workholiday.coffeemaker.wrapper.PageBaseFileWrapper;
import org.workholiday.coffeemaker.wrapper.PageFileWrapper;
import org.workholiday.coffeemaker.wrapper.PageQueryFileWrapper;
import static org.workholiday.coffeemaker.util.StringUtil.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     20:27 06/07/2017
 * Version:  1.0
 * Description: CoffeeMaker启动器
 */
public class CoffeeMakerLauncher {

    private static Logger logger = LoggerFactory.getLogger(CoffeeMakerLauncher.class);


    public static void main(String[] args) {

        // 构建配置参数
        Configuration configuration = buildConfiguration();

        // =========================================
        //
        //             启动代码生成器
        //
        // =========================================
        long start = System.currentTimeMillis();
        new CoffeeMakerLauncher().exec(configuration);
        long finish = System.currentTimeMillis();

        logger.info("======================================================================");
        logger.info("");
        logger.info("     Congratulations! ALL DONE. ");
        logger.info("");
        logger.info("----------------------------------------------------------------------");
        logger.info("     Total cost: {} s", formatLogTime(finish - start));
        logger.info("     Export path: {}", configuration.getOutputPath());
        logger.info("");
        logger.info("======================================================================");
    }

    /**
     * 构建配置参数
     * @return
     */
    private static Configuration buildConfiguration() {

        Configuration configuration = new Configuration();
        configuration.setTableName("t_user")
            .setTablePrefix("")
            .setPackageName("com.workholiday")
            .setPagerPackageName("com.workholiday.base.core.page")
            .setWithPager(true)
            .setOutputPath("/Users/hunterzhao/tmp/output");

        // 配置注释
        JavaFileComment fileComment = new JavaFileComment();
        fileComment.setAuthor("Hunter Zhao");
        fileComment.setMail("zhaohe@gmail.com");
        fileComment.setDate(DateUtil.format(new Date(), DateUtil.PATTERN_HH_MM_YYYY_MM_DD));
        fileComment.setVersion("1.0");

        configuration.setFileComment(fileComment);
        return configuration;
    }


    /**
     * 启动代码生成器
     */
    private void exec(Configuration configuration) {

        logger.info("======================================================================");
        logger.info("");
        logger.info("             CoffeeMaker now is launching....                          ");
        logger.info("");
        logger.info("======================================================================");

        logger.info("Preparing basic data...");
        logger.info("");

        String basicDataStatus = " preparation status";

        // 1、准备表元数据信息
        TableMetaData tableMetaData = this.prepareTableMetaData(configuration.getTableName());
        printStatsLog("TableMetaData" + basicDataStatus);

        // 2、准备实体文件定义
        JavaFileDefinition entityFileDefinition =
                this.prepareEntityFileDefinition(tableMetaData, configuration);

        printStatsLog("EntityFileDefinition" + basicDataStatus);

        // 3、开始执行
        this.emit(entityFileDefinition, tableMetaData, configuration);
    }


    /**
     * 准备表元数据信息
     * @param tableName
     * @return
     */
    private TableMetaData prepareTableMetaData(String tableName) {

        Objects.requireNonNull(tableName);

        TableMetaDataProvider provider = new TableMetaDataProvider();
        // 获取表信息元数据
        TableMetaData tableMetaData = provider.fetchMetaData(tableName);

        return tableMetaData;
    }


    /**
     * 准备实体文件定义
     * @param tableMetaData
     * @param configuration
     * @return
     */
    private JavaFileDefinition prepareEntityFileDefinition(TableMetaData tableMetaData, Configuration configuration) {

        Objects.requireNonNull(tableMetaData);
        Objects.requireNonNull(configuration.getPackageName());

        FileDefinitionConverter<JavaFileDefinition, TableMetaData> converter = new JavaFileDefinitionConverter(configuration);
        // 将表元数据转换成Java文件定义对象
        JavaFileDefinition entityDefinition = converter.convert(tableMetaData);
        // 设置包名
        entityDefinition.setPackageName(configuration.getPackageName());
        // 设置文件注释
        entityDefinition.setFileComment(configuration.getFileComment());
        // 设置输出目录
        entityDefinition.setOutputPath(configuration.getOutputPath());
        return entityDefinition;
    }


    /**
     * 渲染并输出文件
     * @param entityDef
     * @param tableMetaData
     */
    private void emit(JavaFileDefinition entityDef, TableMetaData tableMetaData, Configuration configuration) {

        Objects.requireNonNull(entityDef);
        Objects.requireNonNull(tableMetaData);
        Objects.requireNonNull(configuration.getOutputPath());

        logger.info("----------------------------------------------------------------------");
        logger.info("Preparing FileWrappers...");
        logger.info("");

        List<AbstractFileWrapper> fileWrappers = new ArrayList<>();
        FileParser parser = new DefaultFileParser();

        String fileWrapperStats = "FileWrapper status";

        //
        // 实体
        //
        JavaFileWrapper<JavaFileDefinition> entityFileWrapper = new EntityFileWrapper(entityDef);
        // 设置输出目录
        fileWrappers.add(entityFileWrapper);
        printStatsLog(entityFileWrapper.getFileRole().getRoleName() + fileWrapperStats);

        //
        // 分页对象基类
        //
        JavaFileWrapper<JavaFileDefinition>  pageBaseFileWrapper = new PageBaseFileWrapper(entityDef, configuration.getPagerPackageName());
        printStatsLog(pageBaseFileWrapper.getFileRole().getRoleName() + fileWrapperStats);

        //
        // 分页查询器
        //
        JavaFileWrapper<JavaFileDefinition> pageQueryFileWrapper = new PageQueryFileWrapper(entityDef, pageBaseFileWrapper, configuration.getPagerPackageName());
        printStatsLog(pageQueryFileWrapper.getFileRole().getRoleName() + fileWrapperStats);

        //
        // 分页对象
        //
        JavaFileWrapper<JavaFileDefinition> pageFileWrapper = new PageFileWrapper(entityDef, pageBaseFileWrapper, configuration.getPagerPackageName());
        printStatsLog(pageFileWrapper.getFileRole().getRoleName() + fileWrapperStats);

        // 如果需要生成Page对象
        if (configuration.isWithPager()) {
            fileWrappers.add(pageBaseFileWrapper);
            fileWrappers.add(pageQueryFileWrapper);
            fileWrappers.add(pageFileWrapper);
        }

        //
        // DAO
        //
        JavaFileWrapper<JavaFileDefinition> daoFileWrapper = new DaoFileWrapper(entityDef, entityFileWrapper, pageFileWrapper, pageQueryFileWrapper);
        fileWrappers.add(daoFileWrapper);
        printStatsLog(daoFileWrapper.getFileRole().getRoleName() + fileWrapperStats);

        //
        // SERVICE
        //
        JavaFileWrapper<JavaFileDefinition> serviceFileWrapper = new ServiceFileWrapper(entityDef, entityFileWrapper, pageFileWrapper, pageQueryFileWrapper);
        fileWrappers.add(serviceFileWrapper);
        printStatsLog(serviceFileWrapper.getFileRole().getRoleName() + fileWrapperStats);

        //
        // SERVICE IMPL
        //
        JavaFileWrapper<JavaFileDefinition> serviceImplFileWrapper =
                new ServiceImplFileWrapper(entityDef, entityFileWrapper, daoFileWrapper, serviceFileWrapper, pageFileWrapper, pageQueryFileWrapper);
        fileWrappers.add(serviceImplFileWrapper);
        printStatsLog(serviceImplFileWrapper.getFileRole().getRoleName() + fileWrapperStats);

        //
        // mapper
        //
        MapperFileWrapper<MybatisMapperFileDefinition> mapperFileWrapper =
                new MapperFileWrapper<>(new MybatisMapperFileDefinition(), entityFileWrapper, daoFileWrapper, pageQueryFileWrapper, tableMetaData);

        fileWrappers.add(mapperFileWrapper);
        printStatsLog(mapperFileWrapper.getFileRole().getRoleName() + fileWrapperStats);


        //
        // VO
        //
        JavaFileWrapper<JavaFileDefinition> voFileWrapper = new VoFileWrapper(entityDef);
        fileWrappers.add(voFileWrapper);
        printStatsLog(voFileWrapper.getFileRole().getRoleName() + fileWrapperStats);

        //
        // Controller
        //
        JavaFileWrapper<JavaFileDefinition> controllerFileWrapper =
                new ControllerFileWrapper(entityDef, entityFileWrapper, serviceFileWrapper, voFileWrapper, pageFileWrapper, pageQueryFileWrapper);
        fileWrappers.add(controllerFileWrapper);
        printStatsLog(controllerFileWrapper.getFileRole().getRoleName() + fileWrapperStats);


        logger.info("======================================================================");
        logger.info("");
        logger.info("     All ready, here we are going to generate generic code!   ");
        logger.info("");
        logger.info("======================================================================");
        //
        //
        // 渲染并输出
        //
        parser.parseAndExport(fileWrappers);
    }

    /**
     * 输出状态日志
     * @param text
     */
    private void printStatsLog(String text) {
        logger.info("{} [ Ready ]", formatLogTextWithDots(text));
    }


}
