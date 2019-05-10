package com.corgi.mongodb.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @title: User
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 15:35
 **/
@Data
@Document(collection = "user")
public class User {

    private String id;

    private String name;

    private Integer age;

    private String synopsis;
}
