package com.tenxcloud.clouddemo.service.rabbit.consumer.basic;

import com.tenxcloud.clouddemo.service.rabbit.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:11
 **/
@Component
@RabbitListener(queues = RabbitService.BASIC_QUEUE)
@Slf4j
public class BasicReceiver {
    @RabbitHandler
    public void process(String msg) {
        log.info("received: {}", msg);
    }
}
