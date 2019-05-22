package com.corgi.mongodb.controller;

import com.corgi.base.vo.Result;
import com.corgi.mongodb.entity.User;
import com.corgi.mongodb.repository.UserRepository;
import com.corgi.mongodb.util.CheckUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @title: UserController
 * @description: Mono<ResponseEntity<Result<?>>> / Flux<?>
 * @author: dengmiao
 * @create: 2019-05-10 15:21
 **/
@RestController
@RequestMapping("mongodb/controller/user")
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
    public Mono<ResponseEntity<Result<?>>> addUser(@Valid @RequestBody User user) {
        user.setId(null);
        CheckUtil.checkName(user.getName());
        return this.userRepository.save(user)
                .flatMap(u -> Mono.just(new ResponseEntity<>(Result.ok(u), HttpStatus.OK)));
        //return this.userRepository.save(user);
    }

    @DeleteMapping("delete/{id}")
    public Mono<ResponseEntity<Result>> deleteUser(@PathVariable String id) {
        return this.userRepository.findById(id)
                // 需要操作数据 用flatMap, 不需要操作数据 只是转换数据 用map
                .flatMap(user -> this.userRepository.delete(user).then(Mono.just(new ResponseEntity<>(Result.ok(), HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity(Result.error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()), HttpStatus.NOT_FOUND));
    }

    @PutMapping("edit/{id}")
    public Mono<ResponseEntity<Result<User>>> editUser(@PathVariable String id, @Valid @RequestBody User user) {
        CheckUtil.checkName(user.getName());
        return this.userRepository.findById(id)
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    u.setSynopsis(user.getSynopsis());
                    return this.userRepository.save(u);
                })
                .map(u -> new ResponseEntity<>(Result.ok(u), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity(Result.error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()), HttpStatus.NOT_FOUND));
    }

    @GetMapping("getById/{id}")
    public Mono<ResponseEntity<Result<User>>> getById(@PathVariable String id) {
        return this.userRepository.findById(id)
                .map(user -> new ResponseEntity<>(Result.ok(user), HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity(Result.error(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.getReasonPhrase()), HttpStatus.NOT_FOUND));
    }

  @GetMapping("getByAge/{start:\\d+}/{end:\\d+}")
  public Flux<User> getByAgeBetween(
      @PathVariable("start") Integer start, @PathVariable("end") Integer end) {
        return this.userRepository.findByAgeBetween(start, end);
    }

  @GetMapping("getByAgeNativeQuery/{start:\\d+}/{end:\\d+}")
  public Flux<User> getByAgeNativeQuery(
      @PathVariable("start") Integer start, @PathVariable("end") Integer end) {
        return this.userRepository.findByNativeQuery(start, end);
    }
}
