package com.corgi.postgres.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @title: CustomWebFilter
 * @description: 拦截HandlerMethod
 * @author: dengmiao
 * @create: 2019-05-07 13:20
 **/
@Component
public class CustomWebFilter implements WebFilter {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        Object handlerMethod = requestMappingHandlerMapping.getHandler(serverWebExchange).toProcessor().peek();
        //注意跨域时的配置，跨域时浏览器会先发送一个option请求，这时候getHandler不会时真正的HandlerMethod
        if(handlerMethod instanceof HandlerMethod){
            Valid valid = ((HandlerMethod) handlerMethod).getMethodAnnotation(Valid.class);
            //do your logic
        }
        //preprocess()
        Mono<Void> response = webFilterChain.filter(serverWebExchange);
        //postprocess()
        return response;
    }
}
