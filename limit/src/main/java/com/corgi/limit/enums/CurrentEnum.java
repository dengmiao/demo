package com.corgi.limit.enums;

/**
 * @description:
 * @author: dengmiao
 * @create: 2019-04-22 11:43
 **/
public enum CurrentEnum {

    MESSAGE("<pre>The specified service is not currently available.</pre>");

    private String message;

    CurrentEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
