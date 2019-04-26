package com.corgi.core.modules.sys.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableName;
import com.corgi.base.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-26 11:57
 **/
@Data
@Accessors(chain = true)
@TableName(value = "sys_demo")
public class SysDemo extends BaseEntity<Long> {

    private JSON json;

    private String name;
}
