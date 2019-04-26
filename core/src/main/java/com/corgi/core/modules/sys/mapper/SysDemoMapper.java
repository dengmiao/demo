package com.corgi.core.modules.sys.mapper;

import com.corgi.core.modules.sys.entity.SysDemo;

import java.util.List;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-26 12:00
 **/
public interface SysDemoMapper {

    List<SysDemo> selectJson();

    void inset(SysDemo sysDemo);
}
