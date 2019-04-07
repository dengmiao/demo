package com.corgi.core.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.corgi.core.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @program: demo
 * @description: 用户-角色
 * @author: dengmiao
 * @create: 2019-04-06 16:44
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Entity
@Table(name = "sys_user_role")
@TableName(value = "sys_user_role")
public class SysUserRole extends BaseEntity<Long> {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 角色id
     */
    private String roleId;
}
