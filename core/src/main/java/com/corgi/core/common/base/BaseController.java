package com.corgi.core.common.base;

import com.corgi.core.common.toolkit.ResultUtil;
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
    public abstract IBaseService<E,ID> getService();

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

    /*@RequestMapping(value = "/getByPage",method = RequestMethod.GET)
    @ResponseBody
    public Result<Page<E>> getByPage(@ModelAttribute PageVo page){

        Page<E> data = getService().findAll(PageUtil.initPage(page));
        return new ResultUtil<Page<E>>().setData(data);
    }*/

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
