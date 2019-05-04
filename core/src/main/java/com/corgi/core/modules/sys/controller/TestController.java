package com.corgi.core.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.corgi.base.toolkit.ResultUtil;
import com.corgi.base.vo.Result;
import com.corgi.core.modules.sys.entity.SysDemo;
import com.corgi.core.modules.sys.entity.SysUser;
import com.corgi.core.modules.sys.mapper.SysDemoMapper;
import com.corgi.limit.annotation.Limit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
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

    @Resource
    private SysDemoMapper sysDemoMapper;

    @RequestMapping("echo")
    public Result echo() {
        return new ResultUtil<>().setData(new SysUser().setId(123456789876543210L).setUsername("test"));
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
                new SysUser().setId(123456789876543210L).setUsername("test").setBirthday(new Date())
        );
    }

    @RequestMapping(value = "limited", method = {RequestMethod.GET})
    @Limit(qps = 0)
    public Result limited() {
        return new ResultUtil<>().setData(
          new SysUser().setId(123456789876543210L).setUsername("test").setBirthday(new Date())
        );
    }

    @GetMapping(value = "jsonType")
    public Result jsonType() {
        return new ResultUtil<>().setData(sysDemoMapper.selectJson());
    }

    @GetMapping("where")
    public Result where() {
        return new ResultUtil<>().setData(sysDemoMapper.selectListWhere(SysDemo.class," del_flag = 0"));
    }

    @GetMapping("wrapper")
    public Result wrapper() {
        QueryWrapper wrapper = new QueryWrapper<SysDemo>().eq("del_flag", 0).likeRight("name", "B").orderByDesc("create_time");
        return new ResultUtil<>().setData(sysDemoMapper.selectList(wrapper));
    }
}
