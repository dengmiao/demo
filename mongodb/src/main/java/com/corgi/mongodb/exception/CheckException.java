package com.corgi.mongodb.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @title: CheckException
 * @description:
 * @author: dengmiao
 * @create: 2019-05-11 17:32
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckException extends RuntimeException {

    /**
     * 字段名
     */
    private String fieldName;

    /**
     * 字段值
     */
    private Object fieldValue;
}
