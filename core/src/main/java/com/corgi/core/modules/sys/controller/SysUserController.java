package com.corgi.core.modules.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.corgi.core.common.toolkit.ObjectUtil;
import com.corgi.core.common.vo.PageVo;
import com.corgi.core.common.vo.Result;
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
public class SysUserController {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 用户分页数据
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Result<?> queryList(PageVo pagevo, SysUser user) {
        Result<IPage<SysUser>> result = new Result<IPage<SysUser>>();
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>(user);
        Page<SysUser> page = new Page<SysUser>(pagevo.getPageNo(), pagevo.getPageSize());
        //排序逻辑 处理
        String column = pagevo.getColumn(), order = pagevo.getOrder();
        if(ObjectUtil.isNotEmpty(column) && ObjectUtil.isNotEmpty(order)) {
            if("asc".equals(order)) {
                queryWrapper.orderByAsc(ObjectUtil.camelToUnderline(column));
            }else {
                queryWrapper.orderByDesc(ObjectUtil.camelToUnderline(column));
            }
        }
        IPage<SysUser> pageList = sysUserService.page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }
}
