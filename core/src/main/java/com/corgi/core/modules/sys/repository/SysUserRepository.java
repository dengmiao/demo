package com.corgi.core.modules.sys.repository;

import com.corgi.base.base.IBaseRepository;
import com.corgi.core.modules.sys.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 * @title: UserRepository
 * @description:
 * @author: dengmiao
 * @create: 2019-05-16 11:48
 **/
@Repository
public interface SysUserRepository extends IBaseRepository<SysUser, Long> {
}
