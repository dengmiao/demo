package com.corgi.core.config.auto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static com.corgi.core.config.auto.CorgiProperties.DEFAULT_PREFIX;

/**
 * @description: corgi特定的配置属性
 * @author: dengmiao
 * @create: 2019-04-11 10:05
 **/
@Data
@ConfigurationProperties(value = DEFAULT_PREFIX)
public class CorgiProperties {

    /**
     * 自动配置默认前缀
     */
    static final String DEFAULT_PREFIX = "corgi.auto";

    /**
     * spring security 忽略鉴权的url集合
     */
    private final List<String> ignoreUrls;

    private final Project project = new Project();

    /**
     * 项目信息
     */
    @Data
    public static class Project {
        /**
         * 名称
         */
        String name = CorgiDefult.Project.name;
        /**
         * 版本
         */
        String version = CorgiDefult.Project.version;
        /**
         *
         */
        String poweredBy = CorgiDefult.Project.poweredBy;
    }

}
