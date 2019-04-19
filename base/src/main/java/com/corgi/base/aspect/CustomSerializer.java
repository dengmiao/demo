package com.corgi.base.aspect;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.corgi.base.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;

/**
 * @program: demo
 * @description: <p>自定义json序列化器</p>
 * 将Long类型序列化为String
 * 解决超过js的Number安全值(-2^53 ~ 2^53)传至前端出现精度问题
 * 用法: 待序列化的属性加<span>@JSONField(serializeUsing = CustomSerializer.class)</span>
 *
 * 纳入切面 用于判定rest API是服务于安卓还是web
 * @io.swagger.annotations.ApiOperation 标识App API;**.controller 包且!@io.swagger.annotations.ApiOperation标识Web API
 * 安卓端@JSONField(serializeUsing = CustomSerializer.class)仍序列化位Number;pc端需序列化String
 * @author: dengmiao
 * @create: 2019-04-10 21:33
 **/
@Component
@Aspect
@Slf4j
public class CustomSerializer implements ObjectSerializer {

    /**
     * 序列化标志位
     */
    private static volatile boolean flag = true;

    /**
     * 切面  App Api切点
     * 带有@ApiOperation注解 id序列化Long
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("@annotation(io.swagger.annotations.ApiOperation)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        if(result instanceof Result) {}
        log.warn("[App REST API] @io.swagger.annotations.ApiOperation {}", Long.class);
        flag = false;
        return result;
    }

    /**
     * 切面 WEB Controller切点
     * controller且不带有@ApiOperation注解 序列化String
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("execution(* *.corgi..*.controller.*.*(..)) && !@annotation(io.swagger.annotations.ApiOperation)")
    public Object aroundExecution(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        if(result instanceof Result) {}
        log.warn("[Web REST API] !@io.swagger.annotations.ApiOperation {}", String.class);
        flag = true;
        return result;
    }

    @Override
    public void write(JSONSerializer serializer, Object o, Object fieldName, Type fieldType, int features) {
        // 待序列化属性本身就是String
        if(o instanceof String) {
            serializer.write(o);
        } else {
            // 待序列化属性本身是Number
            if(flag) {
                serializer.write(o != null ? String.valueOf(o) : null);
            } else {
                serializer.write(o);
            }
        }
    }
}
