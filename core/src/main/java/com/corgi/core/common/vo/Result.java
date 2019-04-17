package com.corgi.core.common.vo;

import com.corgi.core.common.constant.CommonConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 公共返回
 * @author: dengmiao
 * @create: 2019-04-04 17:41
 **/
@Data
@Accessors(chain = true)
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

    @JsonIgnore
    public static final CommonConstant.HttpState OK = CommonConstant.HttpState.OK;

    @JsonIgnore
    public static final CommonConstant.HttpState ERROR = CommonConstant.HttpState.INTERNAL_SERVER_ERROR;

    public void error500(String message) {
        this.message = message;
        this.code = ERROR.getValue();
        this.success = false;
    }

    public void success(String message) {
        this.message = message;
        this.code = OK.getValue();
        this.success = true;
    }

    public static Result<Object> error(String msg) {
        return error(ERROR.getValue(), msg);
    }

    public static Result<Object> error(int code, String msg) {
        Result<Object> r = new Result<>();
        r.setCode(code);
        r.setMessage(msg);
        r.setSuccess(false);
        return r;
    }

    public static Result<Object> ok(String msg) {
        Result<Object> r = new Result<>();
        r.setSuccess(true);
        r.setCode(OK.getValue());
        r.setMessage(msg);
        return r;
    }

    public static Result<Object> ok(Object obj) {
        Result<Object> r = new Result<Object>();
        r.setSuccess(true);
        r.setCode(OK.getValue());
        r.setMessage(OK.getReasonPhrase());
        r.setResult(obj);
        return r;
    }
}
