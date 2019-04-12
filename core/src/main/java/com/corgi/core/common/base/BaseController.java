package com.corgi.core.common.base;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.corgi.core.common.toolkit.ObjectUtil;
import com.corgi.core.common.toolkit.ResultUtil;
import com.corgi.core.common.vo.PageVo;
import com.corgi.core.common.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-09 17:46
 **/
public abstract class BaseController<E, ID extends Serializable> {

    /**
     * 获取service
     * @return
     */
    @Autowired
    public abstract IBaseJpaService<E,ID> getService();

    @Autowired
    public abstract IBaseMybatisService<E> getMybatisService();

    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result<E> get(@PathVariable ID id){
        E entity = getService().get(id);
        return new ResultUtil<E>().setData(entity);
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @ResponseBody
    public Result<List<E>> getAll(){

        List<E> list = getService().getAll();
        return new ResultUtil<List<E>>().setData(list);
    }

    /**
     * 基于mybatis的分页
     * @param pageVo
     * @param e
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Result<IPage<E>> getByPage(@ModelAttribute PageVo pageVo, E e){
        Result<IPage<E>> result = new Result<>();
        QueryWrapper<E> queryWrapper = new QueryWrapper<>(e);
        Page<E> page = new Page<>(pageVo.getPageNo(), pageVo.getPageSize());
        //排序逻辑 处理 todo 多字段排序
        String column = pageVo.getColumn(), order = pageVo.getOrder();
        if(ObjectUtil.isNotEmpty(column) && ObjectUtil.isNotEmpty(order)) {
            if("asc".equals(order)) {
                queryWrapper.orderByAsc(ObjectUtil.camelToUnderline(column));
            }else {
                queryWrapper.orderByDesc(ObjectUtil.camelToUnderline(column));
            }
        }
        IPage<E> pageList = getMybatisService().page(page, queryWrapper);
        result.setSuccess(true);
        result.setResult(pageList);
        return result;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public Result<E> save(@ModelAttribute E entity){

        E e = getService().save(entity);
        return new ResultUtil<E>().setData(e);
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public Result<E> update(@ModelAttribute E entity){

        E e = getService().update(entity);
        return new ResultUtil<E>().setData(e);
    }

    @RequestMapping(value = "/delByIds/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public Result<Object> delAllByIds(@PathVariable ID[] ids){

        for(ID id:ids){
            getService().delete(id);
        }
        return new ResultUtil<Object>().setSuccessMsg("批量通过id删除数据成功");
    }
}
