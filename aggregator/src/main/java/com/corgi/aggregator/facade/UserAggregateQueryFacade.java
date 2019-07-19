package com.corgi.aggregator.facade;

import io.github.lvyahui8.spring.annotation.DataConsumer;
import io.github.lvyahui8.spring.annotation.DataProvider;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @title: UserAggregateQueryFacade
 * @description:
 * @author: dengmiao
 * @create: 2019-07-18 09:52
 **/
@Component
public class UserAggregateQueryFacade {

    @DataProvider("userWithPosts")
    public Map userWithPosts(
            @DataConsumer("user") Map user,
            @DataConsumer("posts") List<Map> posts) {
        user.put("posts", posts);
        return user;
    }
}
