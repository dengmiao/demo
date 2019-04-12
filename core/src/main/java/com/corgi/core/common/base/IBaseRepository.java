package com.corgi.core.common.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @description:
 * @NoRepositoryBean 自定义接口 不会创建接口的实例 必须加此注解
 * @author: dengmiao
 * @create: 2019-04-09 17:42
 **/
@NoRepositoryBean
public interface IBaseRepository<E, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E> {
}
