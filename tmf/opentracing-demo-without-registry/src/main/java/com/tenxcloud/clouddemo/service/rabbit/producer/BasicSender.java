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
public class BasicSender implements RabbitService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private AtomicInteger msgID = new AtomicInteger(1);
    @Override
    public String send(){
        String msg = msg();
        amqpTemplate.convertAndSend(BASIC_QUEUE, msg);
        log.info(msg);
        return msg;
    }

    @Override
    public String msg() {
        int andIncrement = msgID.getAndIncrement();
        return String.format(MSG, BASIC, andIncrement, BASIC_EXCHANGE, BASIC_QUEUE);
    }
}