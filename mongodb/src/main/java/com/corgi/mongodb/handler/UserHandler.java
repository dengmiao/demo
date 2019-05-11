package com.corgi.mongodb.handler;

import com.corgi.mongodb.entity.User;
import com.corgi.mongodb.repository.UserRepository;
import com.corgi.mongodb.util.CheckUtil;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @title: UserHandler
 * @description:
 * @author: dengmiao
 * @create: 2019-05-11 14:34
 **/
@Component
public class UserHandler {

    private final UserRepository userRepository;

    public UserHandler(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> getUserList(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(this.userRepository.findAll(), User.class);
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        // block() 会阻塞线程
        Mono<User> user = request.bodyToMono(User.class);

        return user.flatMap(u -> {
            // 数据校验
            CheckUtil.checkName(u.getName());

            return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                    .body(this.userRepository.save(u), User.class);
        });
    }

    public Mono<ServerResponse> deleteUser(ServerRequest request) {
        String id = request.pathVariable("id");
        return this.userRepository.findById(id)
                .flatMap(user -> this.userRepository.delete(user)
                        .then(ServerResponse.ok().build()))
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
