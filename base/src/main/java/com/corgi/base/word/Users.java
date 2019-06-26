package com.corgi.base.word;

import java.util.Date;

/**
 * @title: Users
 * @description:
 * @author: dengmiao
 * @create: 2019-06-17 10:32
 **/
public class Users {

    private String name;

    private String sex;

    private Integer age;

    private Date birthday;

    public Users(String name, String sex, Integer age, Date birthday) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
