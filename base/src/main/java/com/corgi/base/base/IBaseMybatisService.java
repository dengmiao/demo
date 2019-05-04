package com.corgi.base.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.corgi.base.vo.PageVo;

import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-11 17:55
 **/
public interface IBaseMybatisService<E> extends IService<E> {

    /**
     * 包含多条件、多分组、多排序的list查询
     * @param pageVo
     * @param e
     * @return
     */
    default List<E> listCondition(PageVo pageVo, E e) {
        LambdaQueryWrapper<E> queryWrapper = new LambdaQueryWrapper<>(e);
        if(pageVo.getGroupKey() != null && pageVo.getGroupKey().length > 0) {
            queryWrapper.groupBy();
        }
        return this.list(queryWrapper);
    }
}
