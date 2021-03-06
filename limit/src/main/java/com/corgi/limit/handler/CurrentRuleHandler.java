package com.corgi.limit.handler;

import com.corgi.limit.property.CurrentProperty;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-25 13:18
 **/
public interface CurrentRuleHandler {

    /**
     * 拦截规则
     * @param request
     * @return
     */
    CurrentProperty rule(HttpServletRequest request);
}
