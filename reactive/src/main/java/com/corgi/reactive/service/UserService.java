package com.corgi.reactive.service;

import com.corgi.reactive.domain.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.HashMap;

/**
 * @title: UserService
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 22:11
 **/
@Service
public class UserService {

    private static final HashMap<String, User> dataMap = new HashMap(){
        {
            put("1", new User("1", "user1"));
            put("2", new User("2", "user2"));
            put("3", new User("3", "user3"));
            put("4", new User("4", "user4"));
            put("5", new User("5", "user5"));
        }
    };

    /**
     * 查询用户列表
     * @return
     */
    public Flux<User> list() {
        Collection<User> list = dataMap.values();
        return Flux.fromIterable(list);
    }

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    public Mono<User> findById(final String id) {
        System.out.println(dataMap.get(id));
        return Mono.justOrEmpty(dataMap.get(id));
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    public Mono<User> del(final String id) {
        return Mono.just(dataMap.remove(id));
    }
}
