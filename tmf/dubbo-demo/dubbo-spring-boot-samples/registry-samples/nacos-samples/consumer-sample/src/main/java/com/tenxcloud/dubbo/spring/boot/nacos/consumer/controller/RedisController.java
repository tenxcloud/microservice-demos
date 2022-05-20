/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.dubbo.spring.boot.nacos.consumer.controller;


import com.tenxcloud.dubbo.demo.api.RedisService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.*;

/**
 * @author  zhangdalei
 * @Date 2020-08-12 16:37
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {
    @DubboReference(version = "${demo.service.version}")
    RedisService redisService;
    @GetMapping("/set")
    public void setKV(@RequestParam String k, @RequestParam String v) {
        redisService.setValue(k, v);
    }

    @GetMapping("/get/{k}")
    public Object getKV(@PathVariable String k) {
        return redisService.getValue(k);
    }
}
