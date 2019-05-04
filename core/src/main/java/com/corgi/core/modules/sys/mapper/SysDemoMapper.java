package com.corgi.core.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.corgi.base.base.CustomMapper;
import com.corgi.core.modules.sys.entity.SysDemo;

import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-26 12:00
 **/
public interface SysDemoMapper extends BaseMapper<SysDemo>, CustomMapper<SysDemo, Long> {

    /**
     * selectJson
     * @return
     */
    List<SysDemo> selectJson();

    /**
     * insert
     * @param sysDemo
     */
    void inset(SysDemo sysDemo);
}
