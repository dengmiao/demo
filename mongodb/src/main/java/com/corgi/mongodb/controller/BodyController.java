package com.corgi.mongodb.controller;

import com.corgi.base.vo.Result;
import com.corgi.mongodb.entity.Body;
import com.corgi.mongodb.repository.BodyRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @title: BodyController
 * @description:
 * @author: dengmiao
 * @create: 2019-06-10 09:38
 **/
@RestController
@RequestMapping("mongodb/controller/body")
public class BodyController {

    public final BodyRepository bodyRepository;

    public BodyController(final BodyRepository bodyRepository) {
        this.bodyRepository = bodyRepository;
    }

    @PostMapping("add")
    public Mono<ResponseEntity<Result<?>>> addUser(@Valid @RequestBody Body body) {
        return this.bodyRepository.save(body)
                .flatMap(u -> Mono.just(new ResponseEntity<>(Result.ok(u), HttpStatus.OK)));
    }
}
