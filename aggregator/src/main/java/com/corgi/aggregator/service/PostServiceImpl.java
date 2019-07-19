package com.corgi.aggregator.service;

import io.github.lvyahui8.spring.annotation.DataProvider;
import io.github.lvyahui8.spring.annotation.InvokeParameter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @title: PostServiceImpl
 * @description:
 * @author: dengmiao
 * @create: 2019-07-18 09:19
 **/
@Service
public class PostServiceImpl {

    private static final List<Map<Long, Object>> data = new ArrayList<Map<Long, Object>>(){
        {
            add(new HashMap<Long, Object>(){{ put(1L, "阿斯蒂芬"); }});
            add(new HashMap<Long, Object>(){{ put(2L, "法国海军"); }});
            add(new HashMap<Long, Object>(){{ put(3L, "去微软"); }});
            add(new HashMap<Long, Object>(){{ put(2L, "现场v吧"); }});
            add(new HashMap<Long, Object>(){{ put(1L, "破i阿斯顿"); }});
        }
    };

    @DataProvider("posts")
    public List<Map<Long, Object>> getPosts(@InvokeParameter("userId") final Long userId) {
        try {
            // 模拟耗时
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data.stream().filter(m -> m.containsKey(userId)).collect(Collectors.toList());
    }
}
