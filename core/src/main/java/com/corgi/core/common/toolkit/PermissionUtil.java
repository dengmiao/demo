package com.corgi.core.common.toolkit;

import com.corgi.core.common.annotation.DataScope;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;

import java.lang.reflect.Method;

/**
 * @description: 自定义权限相关工具类
 * @author: dengmiao
 * @create: 2019-04-11 14:00
 **/
@Slf4j
public class PermissionUtil {

    public static DataScope getPermissionByDelegate(MappedStatement mappedStatement) {
        DataScope filterPermission = null;
        try {
            String id = mappedStatement.getId();
            String className = id.substring(0, id.lastIndexOf("."));
            String methodName = id.substring(id.lastIndexOf(".") + 1);
            final Class cls = Class.forName(className);
            final Method[] method = cls.getMethods();
            for (Method me : method) {
                if (me.getName().equals(methodName) && me.isAnnotationPresent(DataScope.class)) {
                    filterPermission = me.getAnnotation(DataScope.class);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterPermission;
    }
}
