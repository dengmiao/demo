package com.corgi.core.common.fastjson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

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
public class CustomSerializer extends JsonSerializer<Long> {

    @Override
    public void serialize(Long aLong, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(aLong != null ? String.valueOf(aLong) : null);
    }
}
