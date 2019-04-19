package com.corgi.core.modules.sys.service;

import com.corgi.base.base.IBaseMybatisService;
import com.corgi.core.modules.sys.entity.SysDept;
import com.corgi.core.modules.sys.model.SysDeptTreeModel;

import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-12 10:36
 **/
public interface ISysDeptService extends IBaseMybatisService<SysDept> {

    /**
     * 查询所有部门信息,并分节点进行显示
     * @return 部门树
     */
    List<SysDeptTreeModel> queryTreeList();
}
