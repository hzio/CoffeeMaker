package org.workholiday.coffeemaker.common;

import org.workholiday.coffeemaker.core.JavaFileComment;

import java.util.Objects;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        17:02 07/30/2017
 * Version:     1.0
 * Description: 配置属性
 */

public class Configuration {


    /**
     * 表名
     */
    String tableName;

    /**
     * 表前缀
     * 默认检查是否以"t"开头
     */
    String tablePrefix;

    /**
     * 包名
     */
    String packageName;

    /**
     * 是否生成Page对象
     */
    boolean withPager;

    /**
     * Page对象所在包名
     */
    String pagerPackageName;

    /**
     * 输出目录
     */
    String outputPath;

    /**
     * 注释模板
     */
    private JavaFileComment fileComment;


    public Configuration setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public Configuration setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
        return this;
    }

    public Configuration setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }

    public Configuration setWithPager(boolean withPager) {
        if (!withPager) {
            Objects.requireNonNull(getPagerPackageName(),"Pager package name must not be specified.");
        }
        this.withPager = withPager;
        return this;
    }

    public Configuration setPagerPackageName(String pagerPackageName) {
        this.pagerPackageName = pagerPackageName;
        return this;
    }

    public Configuration setOutputPath(String outputPath) {
        this.outputPath = outputPath;
        return this;
    }

    public Configuration setFileComment(JavaFileComment fileComment) {
        this.fileComment = fileComment;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public String getPackageName() {
        return packageName;
    }

    public boolean isWithPager() {
        return withPager;
    }

    public String getPagerPackageName() {
        return pagerPackageName;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public JavaFileComment getFileComment() {
        return fileComment;
    }
}
