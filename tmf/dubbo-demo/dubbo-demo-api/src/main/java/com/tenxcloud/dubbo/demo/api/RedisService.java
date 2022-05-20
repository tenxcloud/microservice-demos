package com.tenxcloud.dubbo.demo.api;

import java.util.Map;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 11:48
 **/
public interface RedisService {
    void setValue(String key, Map<String, Object> value);
    void setValue(String key, String value);
    void setValue(String key, Object value);
    Object getMapValue(String key);
    Object getValue(String key);
}
