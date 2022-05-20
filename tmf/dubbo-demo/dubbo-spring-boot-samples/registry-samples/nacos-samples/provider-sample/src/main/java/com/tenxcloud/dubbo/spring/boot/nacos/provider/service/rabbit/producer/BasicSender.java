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
public class BasicSender implements RabbitService {
    @Autowired
    private AmqpTemplate amqpTemplate;
    private AtomicInteger count = new AtomicInteger(1);
    @Override
    public void send(){
        int var = count.get();
        if (var == 1000){
            count.set(1);
        }
        String msg;
        int order = var % 3;
        if (order == 0){
            msg = "tmf_basic_A" + count.getAndIncrement();
            amqpTemplate.convertAndSend("tmf_basic_A", msg);
        }else if(order == 1){
            msg = "tmf_basic_B" + count.getAndIncrement();
            amqpTemplate.convertAndSend("tmf_basic_B", msg);
        }else {
            msg = "tmf_basic_C" + count.getAndIncrement();
            //基本消息模式  直接模式 单对单,routingKey即为Q名
            amqpTemplate.convertAndSend("tmf_basic_C", msg);
        }
        log.info(msg);
    }
}
