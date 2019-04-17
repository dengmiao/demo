package com.corgi.core.config.mybatis;

import com.corgi.core.common.toolkit.ObjectUtil;
import com.corgi.core.common.toolkit.ReflectUtil;
import com.corgi.core.common.toolkit.SecurityUtil;
import com.corgi.core.modules.sys.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Properties;

/**
 * @program: demo
 * @description: mybatis拦截器，自动注入创建人、创建时间、修改人、修改时间
 * @author: dengmiao
 * @create: 2019-04-06 15:40
 **/
@Slf4j
@Component
@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String sqlId = mappedStatement.getId();
        log.debug("------sqlId------" + sqlId);
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        log.info("------sqlCommandType------" + sqlCommandType);

        if (parameter == null) {
            return invocation.proceed();
        }
        if (SqlCommandType.INSERT == sqlCommandType) {
            // parameter.getClass().getDeclaredFields()
            Field[] fields = ReflectUtil.getFields(parameter.getClass());
            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("createBy".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createBy = field.get(parameter);
                        field.setAccessible(false);
                        if (local_createBy == null || local_createBy.equals("")) {
                            String createBy = "1";
                            // 获取登录用户信息
                            SysUser sysUser = SecurityUtil.getLoginUser();
                            if (sysUser != null) {
                                // 登录账号
                                createBy = String.valueOf(sysUser.getId());
                            }
                            if (!ObjectUtil.isEmpty(createBy)) {
                                field.setAccessible(true);
                                field.set(parameter, createBy);
                                field.setAccessible(false);
                            }
                        }
                    }
                    // 注入创建时间
                    if ("createTime".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_createDate = field.get(parameter);
                        field.setAccessible(false);
                        if (local_createDate == null || local_createDate.equals("")) {
                            field.setAccessible(true);
                            field.set(parameter, new Date());
                            field.setAccessible(false);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        if (SqlCommandType.UPDATE == sqlCommandType) {
            Field[] fields = null;
            if (parameter instanceof MapperMethod.ParamMap) {
                MapperMethod.ParamMap<?> p = (MapperMethod.ParamMap<?>) parameter;
                parameter = p.get("param1");
            }
            // parameter.getClass().getDeclaredFields()
            fields = ReflectUtil.getFields(parameter.getClass());

            for (Field field : fields) {
                log.debug("------field.name------" + field.getName());
                try {
                    if ("updateBy".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_updateBy = field.get(parameter);
                        field.setAccessible(false);
                        if (local_updateBy == null || local_updateBy.equals("")) {
                            String updateBy = "1";
                            // 获取登录用户信息
                            try {
                                SysUser sysUser = SecurityUtil.getLoginUser();
                                if (sysUser != null) {
                                    // 登录账号
                                    updateBy = String.valueOf(sysUser.getId());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (!ObjectUtil.isEmpty(updateBy)) {
                                field.setAccessible(true);
                                field.set(parameter, updateBy);
                                field.setAccessible(false);
                            }
                        }
                    }
                    if ("updateTime".equals(field.getName())) {
                        field.setAccessible(true);
                        Object local_updateDate = field.get(parameter);
                        field.setAccessible(false);
                        if (local_updateDate == null || local_updateDate.equals("")) {
                            field.setAccessible(true);
                            field.set(parameter, new Date());
                            field.setAccessible(false);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
