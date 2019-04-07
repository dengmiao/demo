package com.corgi.core.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.corgi.core.modules.sys.entity.SysUser;

/**
 *
 * @author dengmiao
 */
public interface ISysUserService extends IService<SysUser> {

    /**
     * 按用户名查询
     * @param username
     * @return
     */
    SysUser findByUsername(String username);
}
