package com.corgi.limit.handler;

import com.corgi.limit.annotation.Limit;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-22 11:35
 **/
public interface CurrentAspectHandler {

    /**
     * 自定义对CurrentLimiter注解的拦截处理
     *
     * @param pjp
     * @param rateLimiter
     * @return
     * @throws Throwable
     */
    Object around(ProceedingJoinPoint pjp, Limit rateLimiter)throws Throwable;
}
