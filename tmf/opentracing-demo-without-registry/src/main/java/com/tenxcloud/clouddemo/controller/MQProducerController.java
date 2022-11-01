package com.tenxcloud.clouddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/rocketmq")
public class MQProducerController {

    private final RocketMQTemplate rocketMQTemplate;

    public MQProducerController(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @GetMapping("/send/{topic}")
    public String send(@PathVariable String topic) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(new Date());
        switch (topic){

            case "a":
                rocketMQTemplate.syncSend("topic-a", MessageBuilder.withPayload(time).build());
                return "success to topic-a" ;
            case "b":
                rocketMQTemplate.syncSend("topic-b", MessageBuilder.withPayload(time).build());
                return "success to topic-b";
            case "c":
                rocketMQTemplate.syncSend("topic-c", MessageBuilder.withPayload(time).build());
                return "success to topic-c";
            case "d":
                rocketMQTemplate.syncSend("topic-d", MessageBuilder.withPayload(time).build());
                return "success to topic-d";
        }

        return "not match topic";
    }
}