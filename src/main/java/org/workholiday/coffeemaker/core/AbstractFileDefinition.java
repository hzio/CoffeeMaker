package org.workholiday.coffeemaker.core;

import lombok.Data;

/**
 * Function: 抽象文件定义
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     18:46 06/13/2017
 * Version:  1.0
 */
@Data
public abstract class AbstractFileDefinition implements FileDefinition {

    /**
     * 输出路径
     */
    private String outputPath;

}
