package com.corgi.core.common.annotation;

import com.corgi.core.common.enums.DataScopeConstant;

import java.lang.annotation.*;

/**
 * @description: 数据范围过滤
 * @author: dengmiao
 * @create: 2019-04-11 10:57
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    DataScopeConstant value() default DataScopeConstant.DATA_SCOPE_SELF;
}
