package com.corgi.core.common.toolkit;

import com.corgi.core.modules.sys.entity.SysRole;
import com.corgi.core.modules.sys.entity.SysUser;
import com.corgi.core.modules.sys.service.ISysUserRoleService;
import com.corgi.core.modules.sys.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-06 15:47
 **/
@Component
@Slf4j
public class SecurityUtil {

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private ISysUserRoleService iSysUserRoleService;

    /**
     * 获取当前登录用户
     * @return
     */
    public SysUser getCurrUser(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return iSysUserService.findByUsername(user.getUsername());
    }

    /**
     * 获取当前用户数据权限 null代表具有所有权限
     */
    public List<String> getDeparmentIds(){

        /*List<String> deparmentIds = new ArrayList<>();
        SysUser u = getCurrUser();
        // 用户角色
        List<SysRole> userRoleList = iSysUserRoleService.findByUserId(String.valueOf(u.getId()));
        // 判断有无全部数据的角色
        Boolean flagAll = false;
        for(SysRole r : userRoleList){
            if(r.getDataType()==null||r.getDataType().equals(CommonConstant.DATA_TYPE_ALL)){
                flagAll = true;
                break;
            }
        }
        if(flagAll){
            return null;
        }
        // 查找自定义
        return iSysUserRoleService.findDepIdsByUserId(u.getId());*/
        return null;
    }
}
