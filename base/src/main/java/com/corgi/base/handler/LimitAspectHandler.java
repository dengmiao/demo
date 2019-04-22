package com.corgi.base.handler;

import com.corgi.base.constant.CommonConstant;
import com.corgi.base.toolkit.ResultUtil;
import com.corgi.limit.annotation.Limit;
import com.corgi.limit.handler.CurrentAspectHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * @description: 限流拦截 拒绝策略
 * @author: dengmiao
 * @create: 2019-04-22 14:48
 **/
@Component
public class LimitAspectHandler implements CurrentAspectHandler {

    @Override
    public Object around(ProceedingJoinPoint pjp, Limit rateLimiter) throws Throwable {
        CommonConstant.HttpState httpState = CommonConstant.HttpState.OPEN_API_QPS_REQUEST_LIMIT_REACHED;
        return new ResultUtil<>().setErrorMsg(httpState.getValue(), httpState.getReasonPhrase());
    }
}
