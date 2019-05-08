package com.corgi.postgres.handler;

import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @title: GlobalErrorAttributes
 * @description: 设置错误消息
 * @author: dengmiao
 * @create: 2019-05-08 11:44
 **/
@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        Throwable error = getError(request);
        if (error instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) error;
            Map<String, Object> errorAttributes = new LinkedHashMap<>();
            errorAttributes.put("code", responseStatusException.getStatus().value());
            errorAttributes.put("msg", responseStatusException.getMessage());
            errorAttributes.put("data", "");
            return errorAttributes;
        } else {
            Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
            errorAttributes.put("code", errorAttributes.getOrDefault("status", 404));
            errorAttributes.put("msg", error.getMessage());
            errorAttributes.put("data", "");
            return errorAttributes;
        }
    }
}
