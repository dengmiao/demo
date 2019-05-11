package com.corgi.mongodb.handler;

import com.corgi.mongodb.exception.CheckException;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

/**
 * @title: ExceptionHandler
 * @description: 异常处理
 * @author: dengmiao
 * @create: 2019-05-11 17:09
 **/
@Component
@Order(-2)
public class ExceptionHandler implements WebExceptionHandler {

    @Override
    public Mono<Void> handle(ServerWebExchange serverWebExchange, Throwable throwable) {
        ServerHttpResponse serverResponse = serverWebExchange.getResponse();
        // 设置响应头 400
        serverResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        // 设置返回类型
        serverResponse.getHeaders().setContentType(MediaType.TEXT_PLAIN);

        // 异常信息
        String errorMsg = toStr(throwable);
        DataBuffer db = serverResponse.bufferFactory().wrap(errorMsg.getBytes());
        return serverResponse.writeWith(Mono.just(db));
    }

    private String toStr(Throwable throwable) {
        // 已知异常
        if(throwable instanceof CheckException) {
            CheckException e = (CheckException) throwable;
            return e.getFieldName() + ": invalid value " + e.getFieldValue();
        }
        // 未知异常
        else {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }
}
