package com.corgi.core.modules.sys.controller.common;

import com.corgi.base.toolkit.ResultUtil;
import com.corgi.base.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-07 15:02
 **/
@RestController
@RequestMapping("/common")
public class SecurityController {

    @RequestMapping(value = "/needLogin",method = RequestMethod.GET)
    public Result<Object> needLogin(){
        return new ResultUtil<>().setErrorMsg(401, "您还未登录");
    }
}
