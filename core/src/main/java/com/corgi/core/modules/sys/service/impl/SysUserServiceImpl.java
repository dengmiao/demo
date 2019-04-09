package com.corgi.core.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.corgi.core.modules.sys.entity.SysPermission;
import com.corgi.core.modules.sys.entity.SysRole;
import com.corgi.core.modules.sys.mapper.SysPermissionMapper;
import com.corgi.core.modules.sys.mapper.SysUserMapper;
import com.corgi.core.modules.sys.mapper.SysUserRoleMapper;
import com.corgi.core.modules.sys.entity.SysUser;
import com.corgi.core.modules.sys.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-06 16:16
 **/
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public SysUser findByUsername(String username) {
        List<SysUser> list =  sysUserMapper.selectByUsername(username);
        if(list != null && list.size() == 1) {
            SysUser sysUser = list.get(0);
            // 关联角色
            List<SysRole> roleList = sysUserRoleMapper.selectByUserId(String.valueOf(sysUser.getId()));
            sysUser.setRoles(roleList);
            // 关联资源
            List<SysPermission> permissionList = sysPermissionMapper.selectByUserId(String.valueOf(sysUser.getId()));
            sysUser.setPermissions(permissionList);
            return sysUser;
        }
        return null;
    }
}
