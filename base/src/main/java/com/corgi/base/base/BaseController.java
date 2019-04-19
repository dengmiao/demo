package com.corgi.base.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.corgi.base.toolkit.ObjectUtil;
import com.corgi.base.toolkit.ResultUtil;
import com.corgi.base.vo.PageVo;
import com.corgi.base.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
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
    @Transactional(rollbackFor = Exception.class)
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
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    @Transactional(rollbackFor = Exception.class)
    public Result<E> edit(@RequestBody JSONObject jsonObject) {
        Result<E> result = new Result<>();
        Class<E> clazz = (Class <E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        E e = JSON.parseObject(jsonObject.toJSONString(), clazz);
        boolean ok = getMybatisService().updateById(e);
        if(ok) {
            result.success("操作成功");
        } else {
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 单表单删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @Transactional(rollbackFor = Exception.class)
    public Result<E> delete(@RequestParam(name="id") String id) {
        Result<E> result = new Result<>();
        boolean ok = getMybatisService().removeById(id);
        if(ok) {
            result.success("操作成功");
        } else {
            result.error500("操作失败");
        }
        return result;
    }

    /**
     * 单表批量删除
     * @param ids
     * @return
     */
    @RequestMapping(value = "/deleteBatch", method = RequestMethod.DELETE)
    @Transactional(rollbackFor = Exception.class)
    public Result<E> deleteBatch(@RequestParam(name="ids") String ids) {
        Result<E> result = new Result<>();
        if(ids==null || "".equals(ids.trim())) {
            result.error500("参数不识别");
        } else {
            boolean ok = getMybatisService().removeByIds(Arrays.asList(ids.split(",")));
            if(ok) {
                result.success("操作成功");
            } else {
                result.error500("操作失败");
            }
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
