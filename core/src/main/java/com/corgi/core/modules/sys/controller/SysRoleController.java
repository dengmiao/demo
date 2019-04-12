package com.corgi.core.modules.sys.controller;

import com.corgi.core.common.base.BaseController;
import com.corgi.core.common.base.IBaseJpaService;
import com.corgi.core.common.base.IBaseMybatisService;
import com.corgi.core.modules.sys.entity.SysRole;
import com.corgi.core.modules.sys.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 角色controller
 * @author: dengmiao
 * @create: 2019-04-12 09:27
 **/
@RestController
@RequestMapping("sys/role")
public class SysRoleController extends BaseController<SysRole, Long> {

    @Autowired
    private ISysRoleService sysRoleService;

    @Override
    public IBaseJpaService<SysRole, Long> getService() {
        return null;
    }

    @Override
    public IBaseMybatisService<SysRole> getMybatisService() {
        return sysRoleService;
    }
}
