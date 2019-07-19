package com.corgi.aggregator.service;

import io.github.lvyahui8.spring.annotation.DataProvider;
import io.github.lvyahui8.spring.annotation.InvokeParameter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @title: UserServiceImpl
 * @description:
 * @author: dengmiao
 * @create: 2019-07-18 09:21
 **/
@Service
public class UserServiceImpl {

    private static final Map<Long, Object> data = new HashMap<Long, Object>(){
        {
            put(1L, "张三");
            put(2L, "李四");
            put(3L, "王五");
            put(4L, "赵六");
        }
    };

    @DataProvider("user")
    public Map get(@InvokeParameter("userId") Long id) {
        try {
            // 模拟耗时
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new HashMap(1){{ put("name", data.get(id)); }};
    }
}
