package com.corgi.core.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.corgi.base.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @description: 角色权限
 * @author: dengmiao
 * @create: 2019-04-09 14:06
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "sys_role_permission")
@TableName(value = "sys_role_permission")
public class SysRolePermission extends BaseEntity<Long> {

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限id
     */
    private String permissionId;
}
