package com.tenxcloud.clouddemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author  zhangdalei
 * @Date 2020-08-12 16:37
 **/
@RestController
@RequestMapping("/mysql")
public class MysqlController {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/routes")
    public List getRoutes() {
        String sql = "SELECT id FROM zuul_routes";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/route/{id}")
    public List<Map<String, Object>> getRoute(@PathVariable int id) {
        String sql = "SELECT * FROM zuul_routes WHERE id = %s";
        return jdbcTemplate.queryForList(String.format(sql, id));
    }

    @GetMapping("/degrades")
    public List<Map<String, Object>> getDegrades() {
        String sql = "SELECT id FROM degrade_config";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/degrade/{id}")
    public List<Map<String, Object>> getDegrade(@PathVariable int id) {
        String sql = "SELECT * FROM degrade_config WHERE id = %s";
        return jdbcTemplate.queryForList(String.format(sql, id));
    }

    @GetMapping("/configs")
    public List<Map<String, Object>> getConfigs() {
        String sql = "SELECT id FROM config_info";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping("/config/{id}")
    public List<Map<String, Object>> getConfig(@PathVariable int id) {
        String sql = "SELECT * FROM config_info WHERE id = %s";
        return jdbcTemplate.queryForList(String.format(sql, id));
    }
}

