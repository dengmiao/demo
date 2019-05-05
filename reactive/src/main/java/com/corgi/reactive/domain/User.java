package com.corgi.reactive.domain;

/**
 * @title: User
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 22:09
 **/
public class User {

    private String id;

    private String name;

    public User() {
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
