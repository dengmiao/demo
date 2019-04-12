package com.corgi.core.modules.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.corgi.core.modules.sys.entity.SysRole;
import com.corgi.core.modules.sys.mapper.SysRoleMapper;
import com.corgi.core.modules.sys.service.ISysRoleService;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-12 09:29
 **/
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
}
