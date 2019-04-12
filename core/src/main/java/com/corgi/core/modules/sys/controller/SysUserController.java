package com.corgi.core.modules.sys.controller;

import com.corgi.core.common.base.BaseController;
import com.corgi.core.common.base.IBaseJpaService;
import com.corgi.core.common.base.IBaseMybatisService;
import com.corgi.core.modules.sys.entity.SysUser;
import com.corgi.core.modules.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户controller
 * @author: dengmiao
 * @create: 2019-04-10 14:20
 **/
@RestController
@RequestMapping("sys/user")
public class SysUserController extends BaseController<SysUser, Long> {

    @Autowired
    private ISysUserService sysUserService;

    @Override
    public IBaseJpaService<SysUser, Long> getService() {
        return null;
    }

    @Override
    public IBaseMybatisService<SysUser> getMybatisService() {
        return sysUserService;
    }
}
