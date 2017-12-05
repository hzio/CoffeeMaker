package org.workholiday.coffeemaker.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.workholiday.coffeemaker.util.FileUtil;
import org.workholiday.coffeemaker.util.ObjectUtil;
import org.workholiday.coffeemaker.util.StringUtil;
import org.workholiday.coffeemaker.util.VelocityUtil;
import org.workholiday.coffeemaker.wrapper.AbstractFileWrapper;

import java.io.File;
import java.util.List;

/**
 * Function: 默认文件解析器
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     01:05 06/10/2017
 * Version:  1.0
 */
public class DefaultFileParser implements FileParser {

    private static Logger logger = LoggerFactory.getLogger(DefaultFileParser.class);

    @Override
    public String parse(AbstractFileWrapper fileWrapper) {
        return VelocityUtil.parse(fileWrapper.getTemplatePath(), fileWrapper);
    }

    @Override
    public void export(String targetPath, String targetFile, String source) {
        FileUtil.write(targetPath, targetFile, source);
    }

    @Override
    public void parseAndExport(AbstractFileWrapper fileWrapper) {

        // 解析模板
        String source = this.parse(fileWrapper);

        // 输出目标文件
        StringBuilder targetFilePath = new StringBuilder(fileWrapper.getOutputPath())
                .append(File.separator)
                // 把service.impl路径转换为正常的FS路径service/impl
                .append(fileWrapper.getFileRole().getPackagePath().replace(".", File.separator))
                .append(File.separator);

        this.export(targetFilePath.toString(), fileWrapper.getFileFullName(), source);
    }

    @Override
    public void parseAndExport(List<AbstractFileWrapper> fileWrappers) {

        if (ObjectUtil.isNotBlank(fileWrappers)) {
            for (AbstractFileWrapper fileWrapper : fileWrappers) {

                if (fileWrapper == null) {
                    continue;
                }

                long start = System.currentTimeMillis();
                this.parseAndExport(fileWrapper);
                long finish = System.currentTimeMillis();

                String logWithDots = StringUtil.formatLogTextWithDots(fileWrapper.getFileFullName());
                String formattedTime = StringUtil.formatLogTime(finish - start);
                logger.info("{} SUCCESS [ {} s ]", logWithDots, formattedTime);
            }
        }

    }

}
