package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.rabbit.consumer.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:11
 **/
@Component
@RabbitListener(queues = "tmf_topic_C")
@Slf4j
public class TopicReceiverC {
    @RabbitHandler
    public void process(String msg) {
        log.info("received: {}, queue: {}", msg, "tmf_topic_C");
    }
}
