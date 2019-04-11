package com.corgi.core.config.mybatis;

import com.baomidou.mybatisplus.core.toolkit.PluginUtils;
import com.corgi.core.common.annotation.DataScope;
import com.corgi.core.common.toolkit.PermissionUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.statement.select.Select;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.io.StringReader;
import java.sql.Connection;
import java.util.Objects;
import java.util.Properties;

import static com.baomidou.mybatisplus.core.toolkit.PluginUtils.DELEGATE_BOUNDSQL_SQL;

/**
 * @description: 数据权限拦截器
 * @author: dengmiao
 * @create: 2019-04-11 13:45
 **/
@Slf4j
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataScopeInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = PluginUtils.realTarget(invocation.getTarget());
        if (statementHandler instanceof RoutingStatementHandler) {
            MetaObject metaObject = SystemMetaObject.forObject(statementHandler);
            MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
            //如果没有@DataScope注解，说明不进行权限拦截，放行
            DataScope filterPermission = PermissionUtil.getPermissionByDelegate(mappedStatement);
            if (Objects.isNull(filterPermission)) {
                return invocation.proceed();
            }
            //根据当前用户的数据范围拼接SQL语句
            log.info("------------------------数据权限过滤开始------------------------");
            metaObject.setValue(DELEGATE_BOUNDSQL_SQL,
                    permissionSql((String) metaObject.getValue(DELEGATE_BOUNDSQL_SQL)));
            log.info("------------------------数据权限过滤结束------------------------");
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

    /**
     * 权限sql包装
     * @param sql
     * @return
     */
    private String permissionSql(String sql) {
        try {
            CCJSqlParserManager parserManager = new CCJSqlParserManager();
            Select select = (Select) parserManager.parse(new StringReader(sql));
            select.getSelectBody().accept(new SelectVisitorImpl());
        } catch (JSQLParserException e) {
            e.printStackTrace();
        }
        return sql;
    }
}
