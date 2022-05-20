package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.mysql;

import com.tenxcloud.dubbo.demo.api.MysqlService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by bzheng on 2021/6/7.
 */
@DubboService(version = "${demo.service.version}")
public class MysqlServiceImpl implements MysqlService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List getRoutes() {
        String sql = "SELECT id FROM zuul_routes";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getRoute(int id) {
        String sql = "SELECT * FROM zuul_routes WHERE id = %s";
        return jdbcTemplate.queryForList(String.format(sql, id));
    }

    public List<Map<String, Object>> getDegrades() {
        String sql = "SELECT id FROM degrade_config";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getDegrade(int id) {
        String sql = "SELECT * FROM degrade_config WHERE id = %s";
        return jdbcTemplate.queryForList(String.format(sql, id));
    }

    public List<Map<String, Object>> getConfigs() {
        String sql = "SELECT id FROM config_info";
        return jdbcTemplate.queryForList(sql);
    }

    public List<Map<String, Object>> getConfig(int id) {
        String sql = "SELECT * FROM config_info WHERE id = %s";
        return jdbcTemplate.queryForList(String.format(sql, id));
    }
}
