package com.corgi.core.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.corgi.core.common.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @program: demo
 * @description: 系统角色
 * @author: dengmiao
 * @create: 2019-04-06 15:51
 **/
@Data
@Entity
@Table(name = "sys_role")
@TableName("sys_role")
public class SysRole extends BaseEntity<Long> {

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 描述
     */
    private String description;
}
