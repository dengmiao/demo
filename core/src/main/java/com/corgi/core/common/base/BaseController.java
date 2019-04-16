package com.corgi.core.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.corgi.core.common.toolkit.ObjectUtil;
import com.corgi.core.common.toolkit.ResultUtil;
import com.corgi.core.common.vo.PageVo;
import com.corgi.core.common.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-09 17:46
 **/
@Slf4j
public abstract class BaseController<E, ID extends Serializable> {

    /**
     * 获取基于jpa的service
     * @return
     */
    @Autowired
    public abstract IBaseJpaService<E,ID> getService();

    /**
     * 获取基于mybatis的service
     * @return
     */
    @Autowired
    public abstract IBaseMybatisService<E> getMybatisService();

    /**
     * 基于mybatis的分页
     * @param pageVo
     * @param e
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public Result<?> getByPage(@ModelAttribute PageVo pageVo, E e){
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
        return Result.ok(pageList);
    }

    /**
     * 单表添加
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<E> add(@RequestBody JSONObject jsonObject) {
        Result<E> result = new Result<>();
        try {
            Class<E> clazz = (Class <E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            E e = JSON.parseObject(jsonObject.toJSONString(), clazz);
            getMybatisService().save(e);
            result.success("操作成功");
        } catch (Exception ex) {
            log.error(ex.getMessage());
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 单表修改
     * @param jsonObject
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public Result<E> edit(@RequestBody JSONObject jsonObject) {
        Result<E> result = new Result<>();
        Class<E> clazz = (Class <E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        E e = JSON.parseObject(jsonObject.toJSONString(), clazz);
        boolean ok = getMybatisService().updateById(e);
        if(ok) {
            result.success("操作成功");
        }
        return result;
    }

    /******************************************************************************************************************/

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
