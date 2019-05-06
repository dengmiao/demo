package com.corgi.reactive;

import com.corgi.reactive.domain.User;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @title: TestClient
 * @description: 测试客户端
 * @author: dengmiao
 * @create: 2019-05-06 11:16
 **/
public class TestClient {

    public static void main(String[] args) {
        WebClient client = WebClient.create("http://localhost:6789/");

        // 请求方法,get,post...
        Mono<User> result = client.get()
                // 请求相对地址以及参数
                .uri("user/findById/{id}/", "1")
                // 请求类型
                .accept(MediaType.APPLICATION_JSON).retrieve()
                // 返回类型
                .bodyToMono(User.class);
        User user = result.block();
        System.out.println(user);
    }
}
