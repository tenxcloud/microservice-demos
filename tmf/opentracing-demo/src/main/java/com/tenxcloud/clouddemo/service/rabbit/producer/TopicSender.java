package com.tenxcloud.clouddemo.service.rabbit.producer;

import com.tenxcloud.clouddemo.service.rabbit.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:08
 **/
@Component
@Slf4j
public class TopicSender implements RabbitService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private AtomicInteger count = new AtomicInteger(1);
    @Override
    public String send(){
        String msg = msg();
        /**
         * 第一个参数表示交换机，
         * 第二个参数表示 routing key，
         * 第三个参数即消息。
         */
        amqpTemplate.convertAndSend(RabbitService.TOPIC_EXCHANGE, RabbitService.TOPIC_QUEUE, msg);
        log.info(msg);
        return msg;
    }

    @Override
    public String msg() {
        int id = count.getAndIncrement();
        return String.format(RabbitService.MSG, RabbitService.TOPIC, id, RabbitService.TOPIC_EXCHANGE, RabbitService.TOPIC_QUEUE);
    }
}
