/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.dubbo.spring.boot.nacos.consumer.controller;

import com.tenxcloud.dubbo.demo.api.RocketMQService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rocketmq")
public class RocketMQController {

    @DubboReference(version = "${demo.service.version}")
    private RocketMQService rocketMQService;

    @RequestMapping("/send/{topic}/{msg}")
    public String send(@PathVariable String topic, @PathVariable String msg){
        rocketMQService.sendMsg(topic, msg);
        return "send to " + topic + msg + " success ";
    }

}
