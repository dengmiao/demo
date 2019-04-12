package com.corgi.core.modules.sys.service;

import com.corgi.core.common.base.IBaseMybatisService;
import com.corgi.core.modules.sys.entity.SysPermission;

import java.util.List;

/**
 *
 * @author dengmiao
 */
public interface ISysPermissionService extends IBaseMybatisService<SysPermission> {

    /**
     * 查询用户权限
     * @param username
     * @return
     */
    List<SysPermission> findByUser(String username);
}
