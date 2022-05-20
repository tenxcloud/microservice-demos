/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.dubbo.spring.boot.nacos.consumer.controller;

import com.tenxcloud.dubbo.demo.api.KafkaService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {

    @DubboReference(version = "${demo.service.version}")
    private KafkaService kafkaService;

    @RequestMapping("/send/{topic}/{msg}")
    public String send(@PathVariable String topic, @PathVariable String msg){
        kafkaService.sendMsg(topic, msg);
        return "send to " + topic + msg + " success ";
    }

}
