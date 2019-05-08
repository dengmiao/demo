package com.corgi.postgres.handler;

import com.corgi.base.vo.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        ObjectMapper mapper = new ObjectMapper();
        if (error instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) error;
            Result result = Result.error(responseStatusException.getStatus().value(), responseStatusException.getMessage());
            Map<String, Object> errorAttributes = mapper.convertValue(result, Map.class);
            return errorAttributes;
        } else {
            Map<String, Object> errorAttributes = super.getErrorAttributes(request, includeStackTrace);
            Result result = Result.error((Integer) errorAttributes.getOrDefault("status", 404), error.getMessage());
            errorAttributes = mapper.convertValue(result, Map.class);
            return errorAttributes;
        }
    }
}
