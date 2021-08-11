/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.eureka.provider.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author wangshixiong
 * @date 2021-07-06 12:58 上午
 */
@RestController
@Slf4j
public class HelloController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("hello")
    public String hello(@RequestParam String msg) {
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            throw new RuntimeException("mock exception :" + msg);
        }
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s], Provider [%s] : Hello, %s, time: %s ", System.getenv("MESH_SVC_NAME"), System.getenv("MESH_SVC_VERSION"), applicationName, msg, new Date());
    }
}
