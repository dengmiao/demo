package com.corgi.reactive.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @title: ErrorController
 * @description: error
 * @author: dengmiao
 * @create: 2019-05-06 10:53
 **/
@RestController
public class ErrorController {

    @RequestMapping("error")
    public Mono<String> error() {
        return Mono.just("error");
    }
}
