package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.rabbit.producer;


import com.tenxcloud.dubbo.demo.api.RabbitService;
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
public class FanoutSender implements RabbitService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private AtomicInteger count = new AtomicInteger(1);
    @Override
    public void send(){
        int var = count.get();
        if (var == 1000){
            count.set(1);
        }
        String msg = "tmf_fanout_all" + count.getAndIncrement();
        amqpTemplate.convertAndSend("tmf_fanout", "", msg);
        log.info(msg);
    }
}
