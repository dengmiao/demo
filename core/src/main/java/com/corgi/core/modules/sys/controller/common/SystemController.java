package com.corgi.core.modules.sys.controller.common;

import com.corgi.core.common.toolkit.ResultUtil;
import com.corgi.core.common.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @description: mock
 * @author: dengmiao
 * @create: 2019-04-10 14:13
 **/
@RestController
@RequestMapping("sys")
public class SystemController {

    @RequestMapping("loginfo")
    public Result<?> loginfo() {
        return new ResultUtil().setData(new HashMap(1){
            {
                put("todayIp", 1);
                put("todayVisitCount", 3);
                put("totalVisitCount", 304);
            }
        });
    }
}