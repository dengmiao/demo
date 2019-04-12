package com.corgi.core.modules.sys.model;

import com.corgi.core.modules.sys.entity.SysPermission;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-12 09:56
 **/
@Data
@NoArgsConstructor
public class SysPermissionTree implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    private String key;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 菜单权限编码
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件
     */
    private String component;

    /**
     * 跳转网页链接
     */
    private String url;

    /**
     * 一级菜单跳转地址
     */
    private String redirect;

    /**
     * 菜单排序
     */
    private Integer sortNo;

    /**
     * 类型（0：一级菜单；1：子菜单 ；2：按钮权限）
     */
    private Integer menuType;

    /**
     * 是否叶子节点: 1:是 0:不是
     */
    private Integer isLeaf;

    /**
     * 描述
     */
    private String description;

    /**
     * 删除状态 0正常 1已删除
     */
    private Integer delFlag;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * alwaysShow
     */
    private boolean alwaysShow;
    /**
     * 是否隐藏路由菜单: 0否,1是（默认值0）
     */
    private boolean hidden;

    private List<SysPermissionTree> children;

    public SysPermissionTree(SysPermission permission) {
        this.key = String.valueOf(permission.getId());
        this.id = String.valueOf(permission.getId());
        this.perms = permission.getPerms();
        this.component = permission.getComponent();
        this.createBy = permission.getCreateBy();
        this.createTime = permission.getCreateTime();
        this.delFlag = permission.getDelFlag();
        this.description = permission.getDescription();
        this.icon = permission.getIcon();
        this.isLeaf = permission.getIsLeaf();
        this.menuType = permission.getMenuType();
        this.name = permission.getName();
        this.parentId = String.valueOf(permission.getParentId());
        this.sortNo = permission.getSortNo();
        this.updateBy = permission.getUpdateBy();
        this.updateTime = permission.getUpdateTime();
        this.redirect = permission.getRedirect();
        this.url = permission.getUrl();
        this.hidden = permission.isHidden();
        this.alwaysShow= permission.isAlwaysShow();
        if (permission.getIsLeaf() == 0) {
            this.children = new ArrayList<>();
        }
    }
}
