package com.corgi.core.modules.sys.service;

import com.corgi.base.base.IBaseMybatisService;
import com.corgi.core.modules.sys.entity.SysRole;
import com.corgi.core.modules.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author dengmiao
 */
public interface ISysUserRoleService extends IBaseMybatisService<SysUserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    List<SysRole> findByUserId(@Param("userId") String userId);
}
