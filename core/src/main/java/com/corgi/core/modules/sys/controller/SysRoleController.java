package com.corgi.core.modules.sys.controller;

import com.corgi.base.base.BaseController;
import com.corgi.base.base.IBaseJpaService;
import com.corgi.base.base.IBaseMybatisService;
import com.corgi.base.vo.Result;
import com.corgi.core.modules.sys.entity.SysRole;
import com.corgi.core.modules.sys.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "/queryAll", method = RequestMethod.GET)
    public Result<List<SysRole>> queryAll() {
        Result<List<SysRole>> result = new Result<>();
        List<SysRole> list = sysRoleService.list();
        if(list==null||list.size()<=0) {
            result.error500("未找到角色信息");
        }else {
            result.setResult(list);
            result.setSuccess(true);
        }
        return result;
    }
}
