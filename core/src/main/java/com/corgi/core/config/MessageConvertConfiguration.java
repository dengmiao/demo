package com.corgi.core.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;

/**
 * @description: json convert
 * @author: dengmiao
 * @create: 2019-04-17 17:09
 **/
@Configuration
public class MessageConvertConfiguration {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteMapNullValue);
        // 3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);
        fastConverter.setDefaultCharset(Charset.forName("utf-8"));
        HttpMessageConverter<?> converter = fastConverter;
        return new HttpMessageConverters(converter);
    }
}
