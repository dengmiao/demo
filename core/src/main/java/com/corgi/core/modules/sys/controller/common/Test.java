package com.corgi.core.modules.sys.controller.common;

import com.corgi.core.modules.sys.entity.SysUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-19 16:25
 **/
public class Test {

    public static void main(String[] args) {
        List<SysUser> list = new ArrayList<SysUser>(){
            {
                add(new SysUser().setId(1L).setUsername("a1"));
                add(new SysUser().setId(2L).setUsername("a2"));
                add(new SysUser().setId(3L).setUsername("a3"));
                add(new SysUser().setId(4L).setUsername("a4"));
                add(new SysUser().setId(5L).setUsername("a5"));
            }
        };
        Map<Long, SysUser> map = list.stream().collect(Collectors.toMap(SysUser::getId, a -> a,(k1,k2)->k1));
        System.out.println(map);
    }
}
