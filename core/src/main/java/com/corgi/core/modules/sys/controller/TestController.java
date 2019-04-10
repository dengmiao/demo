package com.corgi.core.modules.sys.controller;

import com.corgi.core.common.toolkit.ResultUtil;
import com.corgi.core.common.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @description: 测试controller
 * @author: dengmiao
 * @create: 2019-04-04 15:04
 **/
@RestController
@RequestMapping("api/auth")
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

    @RequestMapping("logout")
    public Result<?> logout() {
        return new ResultUtil<>().setData(new HashMap(2){
            {
                put("id", 1);
                put("name", "aa");
            }
        });
    }
}
