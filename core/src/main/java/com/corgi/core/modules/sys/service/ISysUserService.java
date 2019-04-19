package com.corgi.core.modules.sys.service;

import com.corgi.base.base.IBaseMybatisService;
import com.corgi.core.modules.sys.entity.SysUser;

/**
 *
 * @author dengmiao
 */
public interface ISysUserService extends IBaseMybatisService<SysUser> {

    /**
     * 按用户名查询
     * @param username
     * @return
     */
    SysUser findByUsername(String username);
}
