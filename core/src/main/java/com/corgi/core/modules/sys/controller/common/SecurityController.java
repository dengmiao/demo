package com.corgi.core.modules.sys.controller.common;

import com.corgi.base.toolkit.ResultUtil;
import com.corgi.base.vo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Result<Object>> needLogin(){
        return new ResponseEntity<>(new ResultUtil<>().setErrorMsg(401, "您还未登录"), HttpStatus.UNAUTHORIZED);
    }
}
