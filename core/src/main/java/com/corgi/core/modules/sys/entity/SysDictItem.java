package com.corgi.core.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.corgi.base.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @description: 字典分项
 * @author: dengmiao
 * @create: 2019-04-11 16:59
 **/
@Data
@Accessors(chain = true)
@Entity
@Table(name = "sys_dict_item")
@TableName(value = "sys_dict_item")
@org.hibernate.annotations.Table(appliesTo = "sys_dict_item",comment = "字典分项")
public class SysDictItem extends BaseEntity<Long> {

    /**
     * 字典id
     */
    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '字典id'")
    private String dictId;

    /**
     * 字典项文本
     */
    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '字典项文本'")
    private String itemText;

    /**
     * 字典项值
     */
    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '字典项值'")
    private String itemValue;

    /**
     * 描述
     */
    @Column(columnDefinition = "varchar(255) DEFAULT NULL COMMENT '描述'")
    private String description;

    /**
     * 排序
     */
    @Column(columnDefinition = "decimal(10,2) DEFAULT NULL COMMENT '排序'")
    private BigDecimal sortOrder;

    /**
     * 状态（1启用 0不启用）
     */
    @Column(columnDefinition = "int(1) DEFAULT NULL COMMENT '状态（1启用 0不启用）'", length = 1)
    private Integer status;
}
