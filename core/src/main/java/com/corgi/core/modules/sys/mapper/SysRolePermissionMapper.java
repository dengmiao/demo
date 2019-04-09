package com.corgi.core.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.corgi.core.modules.sys.entity.SysPermission;
import com.corgi.core.modules.sys.entity.SysRolePermission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-09 14:22
 **/
@Mapper
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {

    /**
     * 按角色id获取
     * @param roleId
     * @return
     */
    @Select("select p.* from sys_role_permission rp left join sys_permission p and p.del_flag = 0 on p.id = rp.permission_id where rp.role_id = #{roleId}")
    List<SysPermission> selectByRoleId(@Param("roleId") String roleId);
}
