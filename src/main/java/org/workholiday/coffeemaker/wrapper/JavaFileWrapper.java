package org.workholiday.coffeemaker.wrapper;

import lombok.Data;
import org.workholiday.coffeemaker.core.ClassType;
import org.workholiday.coffeemaker.core.ImportClass;
import org.workholiday.coffeemaker.core.JavaFileComment;
import org.workholiday.coffeemaker.core.JavaFileDefinition;
import org.workholiday.coffeemaker.util.ObjectUtil;
import org.workholiday.coffeemaker.util.StringUtil;

import java.util.List;

/**
 * Author:      Hunter Zhao
 * Mail:        zhaohevip@gmail.com
 * Date:        14:29 06/15/2017
 * Version:     1.0
 * Description: Java文件封装器
 */
@Data
public abstract class JavaFileWrapper<T extends JavaFileDefinition> extends AbstractFileWrapper<T> {

    public JavaFileWrapper(T def) {
        super(def);
        this.init(def);
    }


    public JavaFileWrapper(T def, String packageName) {
        super(def);
        this.setPackageName(packageName);
        this.init(def);
    }

    /**
     * 初始化
     * @param def
     */
    private void init(T def) {
        // 带有业务角色的文件名
        this.roleFileName = getFileRole().isGeneric() ?
                getFileRole().getRoleName() : def.getClassName() + getFileRole().getRoleName();
        // 是否已指定基础包名
        String pkgName = ObjectUtil.isNotBlank(this.getPackageName())
                ? getPackageName()
                : def.getPackageName() + "." + getFileRole().getPackagePath();
        // 包名
        this.setPackageName(pkgName);
        // 输出文件名
        super.setFileName(this.roleFileName);
        // 全限定名
        this.fullQualifiedName = getPackageName() + "." + getFileName();
        // 设置角色变量名
        this.varName = StringUtil.firstLetter2LowerCase(roleFileName);
        // 初始化注释
        this.initComment(def);
    }

    /**
     * 初始化注释
     * @param def
     */
    private void initComment(T def) {
        JavaFileComment fileComment = new JavaFileComment();
        fileComment.setAuthor(def.getFileComment().getAuthor());
        fileComment.setMail(def.getFileComment().getMail());
        fileComment.setDate(def.getFileComment().getDate());
        fileComment.setVersion(def.getFileComment().getVersion());
        String description = getFileRole().isGeneric() ?
                getFileRole().getRoleCommentName() : def.getClassName() + getFileRole().getRoleCommentName();
        fileComment.setDescription(description);

        this.fileComment = fileComment;
    }

    /**
     * 变量名
     */
    private String varName;

    /**
     * 角色文件名
     */
    private String roleFileName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 类注释
     */
    private JavaFileComment fileComment;

    /**
     * 全限定名
     */
    private String fullQualifiedName;

    /**
     * 文件类型
     * @return
     */
    public abstract ClassType getClassType();

    /**
     * 获取导入类列表
     */
    public abstract List<ImportClass> getImportClasses();
}
