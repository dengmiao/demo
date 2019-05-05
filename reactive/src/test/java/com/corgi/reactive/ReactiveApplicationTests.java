package com.corgi.reactive;

import com.corgi.reactive.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @title: ReactiveApplicationTests
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 22:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactiveApplicationTests {

    private UserService userService;

    @Before
    public void init() {
        userService = new UserService();
    }

    @Test
    public void test() {
        Mono mono = userService.findById("1");
        Flux flux = userService.list();
        System.out.println(mono);
        System.out.println(flux);

    }
}
