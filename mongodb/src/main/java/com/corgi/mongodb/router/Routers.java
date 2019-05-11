package com.corgi.mongodb.router;

import com.corgi.mongodb.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @title: AllRouters
 * @description: routerFunction 配置路由
 * @author: dengmiao
 * @create: 2019-05-11 14:47
 **/
@Configuration
public class Routers {

    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler handler) {
        return RouterFunctions.nest(
                // 相当于controller的总路由 路由前缀 @RequestMapping("mongodb/controller/user")
                RequestPredicates.path("/mongodb/handler/user"),
                // 相当于@RequestMapping
                RouterFunctions.route(RequestPredicates.GET("/list"), handler::getUserList)
                .andRoute(RequestPredicates.POST("/add").and(RequestPredicates.accept(MediaType.APPLICATION_JSON_UTF8)), handler::createUser)
                .andRoute(RequestPredicates.DELETE("/delete/{id}"), handler::deleteUser)
        );
    }
}
