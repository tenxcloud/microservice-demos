/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.dubbo.spring.boot.nacos.consumer.controller;

import com.tenxcloud.dubbo.demo.api.MysqlService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author zhangdalei
 * @Date 2020-08-12 16:37
 **/
@RestController
@RequestMapping("/mysql")
public class MysqlController {
    @DubboReference(version = "${demo.service.version}")
    private MysqlService mysqlService;

    @GetMapping("/routes")
    public List getRoutes() {
        return mysqlService.getRoutes();
    }

    @GetMapping("/route/{id}")
    public List<Map<String, Object>> getRoute(@PathVariable int id) {
        return mysqlService.getRoute(id);
    }

    @GetMapping("/degrades")
    public List<Map<String, Object>> getDegrades() {
        return mysqlService.getDegrades();
    }

    @GetMapping("/degrade/{id}")
    public List<Map<String, Object>> getDegrade(@PathVariable int id) {
        return mysqlService.getDegrade(id);
    }

    @GetMapping("/configs")
    public List<Map<String, Object>> getConfigs() {
        return mysqlService.getConfigs();
    }

    @GetMapping("/config/{id}")
    public List<Map<String, Object>> getConfig(@PathVariable int id) {
        return mysqlService.getConfig(id);
    }
}

