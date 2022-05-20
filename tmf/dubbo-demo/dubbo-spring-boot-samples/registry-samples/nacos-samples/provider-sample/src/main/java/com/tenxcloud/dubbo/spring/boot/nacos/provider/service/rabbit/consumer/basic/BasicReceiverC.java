package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.rabbit.consumer.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:11
 **/
@Component
@RabbitListener(queues = "tmf_basic_C")
@Slf4j
public class BasicReceiverC {
    @RabbitHandler
    public void process(String msg) {
        log.info("received: {}, queue: {}", msg, "tmf_basic_C");
    }
}
