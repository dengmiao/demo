package com.corgi.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @title: TestController
 * @description: test
 * @author: dengmiao
 * @create: 2019-05-05 22:01
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("echo")
    public Mono<String> echo() {
        return Mono.just("hello corgi!");
    }
}
