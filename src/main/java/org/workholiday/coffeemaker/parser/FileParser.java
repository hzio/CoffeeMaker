package org.workholiday.coffeemaker.parser;

import org.workholiday.coffeemaker.wrapper.AbstractFileWrapper;

import java.util.List;

/**
 * Function: 文件解析器
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     01:06 06/10/2017
 * Version:  1.0
 */
public interface FileParser {


    /**
     * 解析模板
     * @param renderer
     */
    String parse(AbstractFileWrapper renderer);


    /**
     * 导出目标文件
     * @param targetPath
     * @param targetFile
     * @param source
     */
    void export(String targetPath, String targetFile, String source);

    /**
     * 解析并输出
     * @param fileWrapper fileWrapper对象
     */
    void parseAndExport(AbstractFileWrapper fileWrapper);


    /**
     * 解析并输出
     * @param fileWrappers fileWrapper列表
     */
    void parseAndExport(List<AbstractFileWrapper> fileWrappers);
}
