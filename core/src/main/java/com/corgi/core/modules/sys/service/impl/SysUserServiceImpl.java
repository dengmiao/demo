package com.corgi.core.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.corgi.core.modules.sys.Mapper.SysUserMapper;
import com.corgi.core.modules.sys.entity.SysUser;
import com.corgi.core.modules.sys.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findByUsername(String username) {
        List<SysUser> list =  sysUserMapper.selectByUsername(username);
        if(list != null && list.size() == 1) {
            // 关联角色

            // 关联资源
            return list.get(0);
        }
        return null;
    }
}
