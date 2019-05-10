package com.corgi.mongodb.controller;

import com.corgi.mongodb.entity.User;
import com.corgi.mongodb.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @title: UserController
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 15:21
 **/
@RestController
@RequestMapping("mongodb/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("list")
    public Flux<User> getUserList() {
        return userRepository.findAll();
    }

    @GetMapping(value = "stream/list", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getUserListAsStream() {
        return userRepository.findAll();
    }

    @PostMapping("add")
    public Mono<User> addUser(@RequestBody User user) {
        return this.userRepository.save(user);
    }

    @DeleteMapping("delete/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable String id) {
        return this.userRepository.findById(id)
                // 需要操作数据 用flatMap, 不需要操作数据 只是转换数据 用map
                .flatMap(user -> this.userRepository.delete(user).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @PutMapping("edit/{id}")
    public Mono<ResponseEntity<User>> editUser(@PathVariable String id, @RequestBody User user) {
        return this.userRepository.findById(id)
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    u.setSynopsis(user.getSynopsis());
                    return this.userRepository.save(u);
                })
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("getById/{id}")
    public Mono<ResponseEntity<User>> getById(@PathVariable String id) {
        return this.userRepository.findById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity(HttpStatus.NOT_FOUND));
    }

    @GetMapping("getByAge/{start}/{end}")
    public Flux<User> getByAgeBetween(@PathVariable("start") Integer start, @PathVariable("end") Integer end) {
        return this.userRepository.findByAgeBetween(start, end);
    }

    @GetMapping("getByAgeNativeQuery/{start}/{end}")
    public Flux<User> getByAgeNativeQuery(@PathVariable("start") Integer start, @PathVariable("end") Integer end) {
        return this.userRepository.findByNativeQuery(start, end);
    }
}
