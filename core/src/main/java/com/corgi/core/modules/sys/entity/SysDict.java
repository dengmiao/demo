package com.corgi.core.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.corgi.base.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @description: 字典类型
 * @author: dengmiao
 * @create: 2019-04-11 16:58
 **/
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_dict")
@TableName(value = "sys_dict")
@org.hibernate.annotations.Table(appliesTo = "sys_dict",comment = "字典类型")
@Where(clause = "del_flag = 0")
public class SysDict extends BaseEntity<Long> {

    /**
     * 字典名称
     */
    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '字典名称'")
    private String dictName;

    /**
     * 字典编码
     */
    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '字典编码'")
    private String dictCode;

    /**
     * 描述
     */
    @Column(columnDefinition = "text DEFAULT NULL COMMENT '描述'")
    private String description;
}
