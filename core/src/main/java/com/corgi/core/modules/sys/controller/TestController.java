package com.corgi.core.modules.sys.controller;

import com.corgi.core.common.toolkit.ResultUtil;
import com.corgi.core.common.vo.Result;
import com.corgi.core.modules.sys.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @description: 测试controller
 * @author: dengmiao
 * @create: 2019-04-04 15:04
 **/
@RestController
@RequestMapping("api/auth")
@Api(tags = "测试api")
public class TestController {

    @RequestMapping("echo")
    public Object echo() {
        return new SysUser().setId(123456789876543210L).setUsername("test");
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

    @RequestMapping(value = "json", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "json", notes = "dengmiao")
    public Result<?> jsonSerializer() {
        return new ResultUtil().setData(
                new SysUser().setId(123456789876543210L).setUsername("test")
        );
    }
}
