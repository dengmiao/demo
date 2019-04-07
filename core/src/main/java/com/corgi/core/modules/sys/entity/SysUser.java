package com.corgi.core.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.corgi.core.common.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

/**
 * @program: demo
 * @description: 系统用户
 * @author: dengmiao
 * @create: 2019-04-06 15:44
 **/
@Data
@Entity
@Table(name = "sys_user")
@TableName(value = "sys_user")
public class SysUser extends BaseEntity<Long> {

    /**
     * 登录账号
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 密码
     */
    private String password;

    /**
     * md5密码盐
     */
    private String salt;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 生日
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date birthday;

    /**
     * 性别（1：男 2：女）
     */
    private Integer sex;

    /**
     * 电子邮件
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 状态(1：正常  2：冻结 ）
     */
    private Integer status;

    /**
     * 用户拥有角色
     */
    @Transient
    @TableField(exist=false)
    private List<SysRole> roles;

    /**
     * 用户拥有的权限
     */
    @Transient
    @TableField(exist=false)
    private List<SysPermission> permissions;
}
