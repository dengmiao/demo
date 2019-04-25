package com.corgi.limit.annotation;

import java.lang.annotation.*;

/**
 * @description: 限流注解
 * @author: dengmiao
 * @create: 2019-04-22 11:28
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Inherited
public @interface Limit {

    /**
     * 每秒并发量
     */
    double qps() default 20;

    /**
     * 初始延迟时间
     */
    long initialDelay() default 0;

    /**
     * 开启快速失败/阻塞
     */
    boolean failFast() default true;

    boolean overflow() default false;
}
