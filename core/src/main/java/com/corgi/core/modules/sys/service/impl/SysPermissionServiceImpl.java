package com.corgi.core.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.corgi.core.modules.sys.mapper.SysPermissionMapper;
import com.corgi.core.modules.sys.entity.SysPermission;
import com.corgi.core.modules.sys.service.ISysPermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-06 14:53
 **/
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermission> findByUser(String username) {
        return sysPermissionMapper.selectByUsername(username);
    }
}
