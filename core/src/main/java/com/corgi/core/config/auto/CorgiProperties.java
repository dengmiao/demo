package com.corgi.core.config.auto;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

import static com.corgi.core.config.auto.CorgiProperties.DEFAULT_PREFIX;

/**
 * @description: corgi定的配置属性
 * @author: dengmiao
 * @create: 2019-04-11 10:05
 **/
@Data
@ConfigurationProperties(value = DEFAULT_PREFIX)
public class CorgiProperties {

    static final String DEFAULT_PREFIX = "corgi.auto";

    private final List<String> ignoreUrls;

}
