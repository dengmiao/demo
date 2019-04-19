package com.corgi.core.modules.sys.controller;

import com.corgi.base.base.BaseController;
import com.corgi.base.base.IBaseJpaService;
import com.corgi.base.base.IBaseMybatisService;
import com.corgi.base.vo.Result;
import com.corgi.core.modules.sys.entity.SysDept;
import com.corgi.core.modules.sys.model.SysDeptTreeModel;
import com.corgi.core.modules.sys.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 部门controller
 * @author: dengmiao
 * @create: 2019-04-12 10:30
 **/
@RestController
@RequestMapping("sys/dept")
public class SysDeptController extends BaseController<SysDept, Long> {

    @Autowired
    private ISysDeptService sysDeptService;

    /**
     * 查询数据 查出所有部门,并以树结构数据格式响应给前端
     * @return
     */
    @RequestMapping(value = "/queryTreeList", method = RequestMethod.GET)
    public Result<?> queryTreeList() {
        List<SysDeptTreeModel> list = sysDeptService.queryTreeList();
        return Result.ok(list);
    }

    @Override
    public IBaseJpaService<SysDept, Long> getService() {
        return null;
    }

    @Override
    public IBaseMybatisService<SysDept> getMybatisService() {
        return sysDeptService;
    }
}
