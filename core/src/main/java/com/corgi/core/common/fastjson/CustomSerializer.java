package com.corgi.core.common.fastjson;

import com.corgi.core.common.vo.Result;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: demo
 * @description: <p>自定义json序列化器</p>
 * 将Long类型序列化为String
 * 解决超过js的Number安全值(-2^53 ~ 2^53)传至前端出现精度问题
 * 用法: 待序列化的属性加<span>@JsonSerialize(using = CustomSerializer.class)</span>
 * @author: dengmiao
 * @create: 2019-04-10 21:33
 **/
@Component
@Aspect
@Slf4j
public class CustomSerializer extends JsonSerializer<Long> {

    /**
     * 序列化标志位
     */
    private static boolean flag = true;

    /**
     * 切面
     * 带有@ApiOperation注解 id序列化Long
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@annotation(io.swagger.annotations.ApiOperation)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        if(result instanceof Result) {}
        log.warn("@io.swagger.annotations.ApiOperation {}", Long.class);
        flag = false;
        return result;
    }

    /**
     * 切面
     * controller且不带有@ApiOperation注解 序列化String
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* *.corgi..*.controller.*.*(..)) && !@annotation(io.swagger.annotations.ApiOperation)")
    public Object aroundExecution(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        if(result instanceof Result) {}
        log.warn("!@io.swagger.annotations.ApiOperation {}", String.class);
        flag = true;
        return result;
    }

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(flag) {
            jsonGenerator.writeString(aLong != null ? String.valueOf(aLong) : null);
        } else {
            jsonGenerator.writeNumber(aLong);
        }
    }
}
