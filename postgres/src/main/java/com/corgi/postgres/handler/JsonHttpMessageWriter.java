package com.corgi.postgres.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.reactivestreams.Publisher;
import org.springframework.core.ResolvableType;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @title: JsonHttpMessageWriter
 * @description: 处理json异常消息的类
 * @author: dengmiao
 * @create: 2019-05-08 11:47
 **/
@Component
public class JsonHttpMessageWriter implements HttpMessageWriter<Map<String, Object>> {

    @Override
    public List<MediaType> getWritableMediaTypes() {
        return Collections.singletonList(MediaType.APPLICATION_JSON_UTF8);
    }

    @Override
    public boolean canWrite(ResolvableType resolvableType, MediaType mediaType) {
        return MediaType.APPLICATION_JSON.includes(mediaType);
    }

    @Override
    public Mono<Void> write(Publisher<? extends Map<String, Object>> publisher, ResolvableType resolvableType, MediaType mediaType, ReactiveHttpOutputMessage reactiveHttpOutputMessage, Map<String, Object> map) {
        return Mono.from(publisher).flatMap(m -> reactiveHttpOutputMessage.writeWith(Mono.just(reactiveHttpOutputMessage.bufferFactory().wrap(transform2Json(m).getBytes()))));
    }

    private String transform2Json(Map<String, Object> sourceMap) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(sourceMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "{" +
                "\"code\":" +
                sourceMap.getOrDefault("code", 500) +
                "," +
                "\"message\":\"" +
                sourceMap.getOrDefault("message", "") +
                "\"," +
                "\"data\":\"" +
                sourceMap.getOrDefault("data", null) +
                "\"}";
    }
}
