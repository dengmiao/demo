package com.corgi.core.modules.sys.controller;

import com.corgi.core.common.toolkit.ResultUtil;
import com.corgi.core.common.vo.Result;
import com.corgi.core.modules.sys.entity.SysPermission;
import com.corgi.core.modules.sys.service.ISysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 权限controller
 * @author: dengmiao
 * @create: 2019-04-09 15:12
 **/
@RestController
@RequestMapping("sys/permission")
public class SysPermissionController {

    @Autowired
    private ISysPermissionService iSysPermissionService;

    /**
     * 查询用户权限
     * @return
     */
    @RequestMapping(value = "queryByUser", method = RequestMethod.GET)
    public Result<List<SysPermission>> queryByUser(HttpServletRequest req) {
        String username = req.getParameter("username");
        List<SysPermission> permissionList = iSysPermissionService.findByUser(username);
        return new ResultUtil<List<SysPermission>>().setData(permissionList, "操作成功");
    }
}
