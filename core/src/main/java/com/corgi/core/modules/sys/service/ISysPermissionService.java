package com.corgi.core.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.corgi.core.modules.sys.entity.SysPermission;

import java.util.List;

/**
 *
 * @author dengmiao
 */
public interface ISysPermissionService extends IService<SysPermission> {

    /**
     * 查询用户权限
     * @param username
     * @return
     */
    List<SysPermission> findByUser(String username);
}
