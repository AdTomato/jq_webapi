package com.authine.cloudpivot.ext.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/10/20 17:39
 */
public class JsonTypeHandler extends BaseTypeHandler<Object> {
    private Class<?> clazz;
//    private final ObjectMapper mapper;

    public JsonTypeHandler() {
        Type rawType = this.getRawType();
//        if (clazz == null) {
//            throw new IllegalArgumentException("Type argument cannot be null");
//        }
//        this.clazz = clazz;
//        this.mapper = mapper;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object t, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JSON.toJSONString(t));
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String s) throws SQLException {
        String sqlJson = resultSet.getString(s);
        if (!StringUtils.isEmpty(sqlJson)) {
            return JSONObject.parseObject(sqlJson, clazz);
        }
        return null;
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int i) throws SQLException {
        String sqlJson = resultSet.getString(i);
        if (!StringUtils.isEmpty(sqlJson)) {
            return JSONObject.parseObject(sqlJson, clazz);
        }
        return null;
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        String sqlJson = callableStatement.getString(i);
        if (!StringUtils.isEmpty(sqlJson)) {
            return JSONObject.parseObject(sqlJson, clazz);
        }
        return null;
    }
}
