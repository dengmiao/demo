package com.corgi.mongodb.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * @title: User
 * @description:
 * @author: dengmiao
 * @create: 2019-05-10 15:35
 **/
@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(min = 1, max = 100)
    private Integer age;

    private String synopsis;
}
