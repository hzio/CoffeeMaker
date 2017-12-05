package org.workholiday.coffeemaker.wrapper;

import lombok.Data;
import org.workholiday.coffeemaker.common.Const;
import org.workholiday.coffeemaker.core.FileRole;
import org.workholiday.coffeemaker.core.JavaFileDefinition;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        22:51 07/19/2017
 * Version:     1.0
 * Description: VO文件封装器
 */
@Data
public class VoFileWrapper extends EntityFileWrapper implements Const {

    public VoFileWrapper(JavaFileDefinition definition) {
        super(definition);
    }

    @Override
    public FileRole getFileRole() {
        return FileRole.VO;
    }

    @Override
    public String getTemplatePath() {
        return FileRole.ENTITY.getRoleName() + TEMPLATE_NAME_SUFFIX;
    }
}
