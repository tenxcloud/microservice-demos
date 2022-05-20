package com.tenxcloud.dubbo.demo.api;

import java.util.List;
import java.util.Map;

/**
 * Created by bzheng on 2021/6/7.
 */
public interface MysqlService {

    List getRoutes();

    List<Map<String, Object>> getRoute(int id);

    List<Map<String, Object>> getDegrades();

    List<Map<String, Object>> getDegrade(int id);

    List<Map<String, Object>> getConfigs();

    List<Map<String, Object>> getConfig(int id);
}
