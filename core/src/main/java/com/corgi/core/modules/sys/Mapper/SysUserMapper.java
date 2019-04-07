package com.corgi.core.modules.sys.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.corgi.core.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 * @author dengmiao
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 按用户名查询
     * @param username
     * @return
     */
    @Select("select * from sys_user where username = #{username}")
    List<SysUser> selectByUsername(String username);
}
