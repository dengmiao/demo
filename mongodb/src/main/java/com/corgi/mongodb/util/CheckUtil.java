package com.corgi.mongodb.util;

import com.corgi.mongodb.exception.CheckException;

import java.util.stream.Stream;

/**
 * @title: CheckUtil
 * @description: 校验工具
 * @author: dengmiao
 * @create: 2019-05-11 17:22
 **/
public class CheckUtil {

    private static final String[] INVALID_NAMES = {"ADMIN", "SUPER"};

    public static void checkName(String value) {
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny()
                .ifPresent(name -> {
                    throw new CheckException("name", value);
                });
    }
}
