/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.rocketmq;


import com.tenxcloud.dubbo.demo.api.RocketMQService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;

@DubboService(version = "${demo.service.version}")
public class RocketMQServiceImpl implements RocketMQService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendMsg(String topic,String msg) {
        rocketMQTemplate.send(topic, MessageBuilder.withPayload(msg).build());
    }
}
