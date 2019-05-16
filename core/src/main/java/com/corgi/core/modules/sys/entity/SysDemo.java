package com.corgi.core.modules.sys.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import com.corgi.base.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Where;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-26 11:57
 **/
@Data
@Accessors(chain = true)
@TableName(value = "sys_demo")
@Where(clause = "del_flag = 0")
public class SysDemo extends BaseEntity<Long> {

    private JSON json;

    private String name;
}
