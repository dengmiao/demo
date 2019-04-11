package com.corgi.core.config.auto;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 自动配置
 * @author: dengmiao
 * @create: 2019-04-11 10:04
 **/
@Data
@Log4j2
@Configuration
@ConditionalOnWebApplication
@EnableConfigurationProperties(value = CorgiProperties.class)
public class CorgiAutoConfiguration {

    /**
     * 注入 CorgiProperties
     */
    private final CorgiProperties corgiProperties;

    /**
     * @param properties {@link CorgiProperties}
     */
    public CorgiAutoConfiguration(CorgiProperties properties) {
        this.corgiProperties = properties;

    }
}
