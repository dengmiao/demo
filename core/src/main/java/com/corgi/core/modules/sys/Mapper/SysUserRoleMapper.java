package com.corgi.core.modules.sys.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.corgi.core.modules.sys.entity.SysRole;
import com.corgi.core.modules.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author dengmiao
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 通过用户id获取
     * @param userId
     * @return
     */
    @Select("select r.* from sys_user_role ur left join sys_role r on r.id = ur.role_id where ur.user_id = #{userId}")
    List<SysRole> selectByUserId(@Param("userId") String userId);
}
