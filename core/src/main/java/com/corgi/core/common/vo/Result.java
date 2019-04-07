package com.corgi.core.common.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @description: 公共返回
 * @author: dengmiao
 * @create: 2019-04-04 17:41
 **/
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 成功标志
     */
    private boolean success;

    /**
     * 返回处理消息
     */
    private String message;

    /**
     * 返回代码
     */
    private Integer code;

    /**
     * 返回数据对象 data
     */
    private T result;

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();
}
