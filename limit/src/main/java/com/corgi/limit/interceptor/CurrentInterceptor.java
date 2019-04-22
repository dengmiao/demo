package com.corgi.limit.interceptor;

import com.corgi.limit.core.RateLimiter;
import com.corgi.limit.enums.CurrentEnum;
import com.corgi.limit.handler.CurrentInterceptorHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-22 11:40
 **/
public class CurrentInterceptor implements HandlerInterceptor {

    private RateLimiter currentLimiter;
    private boolean failFast;
    private CurrentInterceptorHandler interceptorHandler;

    public CurrentInterceptor(RateLimiter currentLimiter, boolean failFast, CurrentInterceptorHandler handler) {
        this.currentLimiter = currentLimiter;
        this.failFast = failFast;
        this.interceptorHandler = handler;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //执行快速失败
        if (failFast) {
            return tryAcquireFailed(request, response, handler);
        }else { //执行阻塞策略
            return currentLimiter.tryAcquire();
        }
    }

    private boolean tryAcquireFailed(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //取到令牌
        if (currentLimiter.tryAcquireFailed()){
            return true;
        }else { //没取到令牌
            if (interceptorHandler == null) {
                response.getWriter().print(CurrentEnum.MESSAGE.getMessage());
            } else {
                interceptorHandler.preHandle(request, response, handler);
            }
            return false;
        }
    }
}
