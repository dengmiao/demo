package com.corgi.base.fastjson.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description: 用以mysql中json格式的字段,进行转换的自定义转换器,转换为实体类的JSON属性
 * @author: dengmiao
 * @create: 2019-04-26 11:54
 **/
@MappedTypes(JSON.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MySqlJsonHandler extends BaseTypeHandler<JSON> {

    /**
     * 设置非空参数
     * @param preparedStatement
     * @param i
     * @param json
     * @param jdbcType
     * @throws SQLException
     */
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, JSON json, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, String.valueOf(json.toJSONString()));
    }

    /**
     * 根据列名，获取可以为空的结果
     * @param resultSet
     * @param s
     * @return
     * @throws SQLException
     */
    @Override
    public JSON getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String sqlJson = resultSet.getString(s);
        if (null != sqlJson){
            // JSON.isValidArray(str)要去掉字符串的空格, 才能得到期望的结果
            String blankSpace = " ";
            if(JSON.isValidObject(sqlJson)) {
                return JSONObject.parseObject(sqlJson);
            }else if(JSON.isValidArray(sqlJson.replaceAll(blankSpace, ""))) {
                return JSONObject.parseArray(sqlJson);
            }
            return null;
        }
        return null;
    }

    /**
     * 根据列索引，获取可以为空的结果
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    @Override
    public JSON getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String sqlJson = resultSet.getString(i);
        if (null != sqlJson){
            return JSONObject.parseObject(sqlJson);
        }
        return null;
    }

    @Override
    public JSON getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String sqlJson = callableStatement.getString(i);
        if (null != sqlJson){
            return JSONObject.parseObject(sqlJson);
        }
        return null;
    }
}
