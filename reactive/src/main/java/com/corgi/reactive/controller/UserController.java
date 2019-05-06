package com.corgi.reactive.controller;

import com.corgi.reactive.domain.User;
import com.corgi.reactive.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 * @title: UserController
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 21:46
 **/
@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping("inst")
    public Mono<User> newInstance() {
        return Mono.just(new User("1", "asd"));
    }

    @GetMapping("findById/{id}/")
    public Mono<User> findById(@PathVariable("id") final String id) {
        return userService.findById(id);
    }

    @GetMapping(value = "list", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<User> list() {
        return userService.list().delayElements(Duration.ofSeconds(1));
    }
}
