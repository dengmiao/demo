package com.corgi.core.modules.sys.controller;

import com.corgi.core.common.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户controller
 * @author: dengmiao
 * @create: 2019-04-10 14:20
 **/
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    /**
     * 用户分页数据
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<?> queryList() {
        return null;
    }
}
