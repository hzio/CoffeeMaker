package org.workholiday.coffeemaker.core;

/**
 * Function:
 * Author:   Hunter Zhao
 * Mail:     zhaohevip@gmail.com
 * Date:     18:20 06/15/2017
 * Version:  1.0
 */
public enum FileRole {

    ENTITY      (false, "Entity",      "实体",        "entity"),
    DAO         (false, "Dao",         "数据访问对象",  "dao"),
    MAPPER      (false, "Mapper",      "映射文件",     "mapper"),
    SERVICE     (false, "Service",     "服务层",       "service"),
    SERVICE_IMPL(false, "ServiceImpl", "服务层实现",    "service.impl"),
    CONTROLLER  (false, "Controller",  "控制层",       "controller"),
    VO          (false, "Vo",          "数据对象",      "vo"),
    PAGE_BASE   (true,  "PageBase",    "分页对象基类",   "page"),
    PAGE_QUERY  (true,  "PageQuery",   "分页对象查询器", "page"),
    PAGE        (true,  "Page",        "分页对象",      "page");


    FileRole(boolean generic, String roleName, String roleCommentName, String packagePath) {
        this.generic = generic;
        this.roleName = roleName;
        this.roleCommentName = roleCommentName;
        this.packagePath = packagePath;
    }

    /**
     * 是否系统通用文件
     */
    private boolean generic;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色注释名称
     */
    private String roleCommentName;

    /**
     * 包路径
     */
    private String packagePath;


    public boolean isGeneric() {
        return generic;
    }

    public void setGeneric(boolean generic) {
        this.generic = generic;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleCommentName() {
        return roleCommentName;
    }

    public void setRoleCommentName(String roleCommentName) {
        this.roleCommentName = roleCommentName;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }
}
