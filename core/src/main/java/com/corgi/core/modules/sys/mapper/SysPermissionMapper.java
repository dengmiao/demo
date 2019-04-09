package com.corgi.core.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.corgi.core.modules.sys.entity.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author dengmiao
 */
@Mapper
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    /**
     * 按用户id获取
     * @param userId
     * @return
     */
    List<SysPermission> selectByUserId(String userId);

    /**
     * 查询用户权限
     * @param username
     * @return
     */
    List<SysPermission> selectByUsername(String username);
}
