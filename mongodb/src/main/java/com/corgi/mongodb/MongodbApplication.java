package com.corgi.mongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

/**
 * @title: MongodbApplication
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 15:34
 **/
@SpringBootApplication
@EnableReactiveMongoRepositories
public class MongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbApplication.class, args);
    }
}
