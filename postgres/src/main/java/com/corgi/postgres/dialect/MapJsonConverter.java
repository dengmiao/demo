package com.corgi.postgres.dialect;

import com.alibaba.fastjson.JSON;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @description:
 * @author: dengmiao
 * @create: 2019-05-05 11:56
 **/
@Converter
public class MapJsonConverter implements AttributeConverter<HashMap<String,String>,String> {
    @Override
    public String convertToDatabaseColumn(HashMap<String,String> map) {
        return JSON.toJSON(map).toString();
    }

    @Override
    public HashMap<String,String> convertToEntityAttribute(String s) {
        Map mapTypes = JSON.parseObject(s);
        HashMap hashMap = new HashMap();
        mapTypes.forEach((k,v)->{
            hashMap.put(k,v);
        });
//        return JSONObject.parseObject(s).toJavaObject(HashMap.class);
        return hashMap;
    }
}
