package org.workholiday.coffeemaker.wrapper;

import lombok.Data;
import org.workholiday.coffeemaker.common.Const;
import org.workholiday.coffeemaker.core.AbstractFileDefinition;
import org.workholiday.coffeemaker.core.FileRole;
import org.workholiday.coffeemaker.core.JavaFileDefinition;
import org.workholiday.coffeemaker.core.XmlFileDefinition;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        10:38 06/15/2017
 * Version:     1.0
 * Description: 抽象文件封装器
 */

@Data
public abstract class AbstractFileWrapper<T extends AbstractFileDefinition> implements Const {

    /**
     * 文件定义
     */
    private T def;

    public AbstractFileWrapper(T def) {
        this.def = def;
        this.outputPath = def.getOutputPath();
    }


    /** 输出文件名 */
    private String fileName;

    /** 模板路径 */
    private String templatePath;

    /** 输出路径 */
    private String outputPath;

    /** 文件全名称 **/
    private String fileFullName;


    /**
     * 获取文件角色
     * @return
     */
    public abstract FileRole getFileRole();


    public String getTemplatePath() {
        return getFileRole().getRoleName() + TEMPLATE_NAME_SUFFIX;
    }


    public String getFileFullName() {

        StringBuilder fullName = new StringBuilder(getFileName());
        if (def instanceof JavaFileDefinition) {
            fullName.append(".java");
        }
        else if (def instanceof XmlFileDefinition){
            fullName.append(".xml");
        }

        return fullName.toString();
    }


}
