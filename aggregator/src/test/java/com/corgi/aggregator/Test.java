package com.corgi.aggregator;

import io.github.lvyahui8.spring.aggregate.facade.DataBeanAggregateQueryFacade;
import io.github.lvyahui8.spring.annotation.DataConsumer;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Map;

/**
 * @title: Test
 * @description:
 * @author: dengmiao
 * @create: 2019-07-18 09:53
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    DataBeanAggregateQueryFacade dataBeanAggregateQueryFacade;

    @org.junit.Test
    public void test() throws InterruptedException, IllegalAccessException, InvocationTargetException {
        Map user = dataBeanAggregateQueryFacade.get(/*data id*/ "userWithPosts",
                /*Invoke Parameters*/
                Collections.singletonMap("userId",1L),
                Map.class);
        System.out.println(user);
    }
}
