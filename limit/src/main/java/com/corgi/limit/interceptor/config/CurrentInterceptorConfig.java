package com.corgi.limit.interceptor.config;

import com.corgi.limit.core.RateLimiter;
import com.corgi.limit.handler.CurrentInterceptorHandler;
import com.corgi.limit.interceptor.CurrentInterceptor;
import com.corgi.limit.interceptor.properties.CurrentProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-22 11:46
 **/
@Configuration
@ConditionalOnProperty(prefix = "current.limiting", name = "enabled", havingValue = "true")
public class CurrentInterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CurrentProperties properties;

    @Autowired(required = false)
    private CurrentInterceptorHandler handler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new CurrentInterceptor(RateLimiter.of(properties.getQps(),properties.getInitialDelay()),properties.isFailFast(),handler)).addPathPatterns("/**");
    }
}
