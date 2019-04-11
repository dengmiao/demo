package com.corgi.core.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.corgi.core.config.mybatis.DataPermissionSqlParser;
import com.corgi.core.config.mybatis.DataScopeInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: demo
 * @description:
 * @author: dengmiao
 * @create: 2019-04-06 15:37
 **/
@Configuration
@MapperScan({"com.corgi.**.mapper"})
public class MybatisPlusConfig {

    /**
     * 分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor interceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        //数据权限解析
        sqlParserList.add(new DataPermissionSqlParser());
        interceptor.setSqlParserList(sqlParserList);
        return interceptor;
    }

    /**
     * 配置数据权限过滤带有逻辑删除
     * @return
     */
    @Bean
    public ISqlInjector dataScopeSqlInjector() {
        return new LogicSqlInjector();
    }

    /**
     * 数据权限过滤
     * @return
     */
    @Bean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }
}
