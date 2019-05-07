package com.corgi.postgres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: 启动类
 * @author: dengmiao
 * @create: 2019-05-05 13:55
 **/
@SpringBootApplication
public class PostgresApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostgresApplication.class, args);
        //new SpringApplicationBuilder(PostgresApplication.class).web(WebApplicationType.REACTIVE).run(args);
    }
}
