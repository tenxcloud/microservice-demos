/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.kafka;

import com.tenxcloud.dubbo.demo.api.KafkaService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

@DubboService(version = "${demo.service.version}")
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendMsg(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
    }
}
