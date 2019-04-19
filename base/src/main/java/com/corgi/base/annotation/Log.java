package com.corgi.base.annotation;

import com.corgi.base.constant.CommonConstant;

import java.lang.annotation.*;

/**
 * @description: 日志注解
 * @author: dengmiao
 * @create: 2019-04-10 15:51
 **/
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.METHOD)
public @interface Log {

    /**
     * 模块
     */
    String model();

    /**
     * 操作类型
     */
    String action();

    /**
     * 日志类型
     * 0:操作日志;1:登录日志;2:定时任务;
     */
    int logType() default CommonConstant.LogType.OPERATE;

    /**
     * 操作人类别
     */
    int operatorType() default CommonConstant.OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;
}
