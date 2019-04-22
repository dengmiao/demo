package com.corgi.limit.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-22 11:39
 **/
public interface CurrentInterceptorHandler {

    /**
     * 自定义全局的拦截处理
     * true:表示放行，false:表示拦截
     * @param request
     * @param response
     * @param handler
     * @throws Exception
     */
    void preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
