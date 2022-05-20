/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.dubbo.spring.boot.nacos.consumer.controller;


import com.tenxcloud.dubbo.demo.api.RabbitService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  zhangdalei
 * @Date 2020-08-12 16:37
 **/
@RestController
@RequestMapping("/rabbit")
public class RabbitController {

    @DubboReference(version = "${demo.service.version}")
    RabbitService rabbitService;

    @GetMapping("/send/{type}")
    public String send(@PathVariable String type) {
        rabbitService.send(type);
        return "发送成功成功！！！";
    }
}
