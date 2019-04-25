package com.corgi.limit;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-25 12:59
 **/
@Configuration
@ConditionalOnWebApplication
@ComponentScan
public class SpringBootStarterLimitingAutoConfiguration {
}
