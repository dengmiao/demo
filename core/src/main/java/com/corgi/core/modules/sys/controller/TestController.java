package com.corgi.core.modules.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @description: 测试controller
 * @author: dengmiao
 * @create: 2019-04-04 15:04
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @RequestMapping("echo")
    public Object echo() {
        return new HashMap<String, Object>(16){
            {
                put("name", "张三");
                put("age", 25);
                put("habit", "coding");
            }
        };
    }
}
