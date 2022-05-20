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
public class DirectSender implements RabbitService {
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
            msg = "tmf_direct_A" + count.getAndIncrement();
            amqpTemplate.convertAndSend("tmf_direct","direct.a", msg);
        }else if(order == 1){
            msg = "tmf_direct_B" + count.getAndIncrement();
            amqpTemplate.convertAndSend("tmf_direct","direct.b", msg);
        }else {
            msg = "tmf_direct_C" + count.getAndIncrement();
            /**
             * 第一个参数表示交换机，
             * 第二个参数表示 routing key，
             * 第三个参数即消息。
             */
            amqpTemplate.convertAndSend("tmf_direct","direct.c", msg);
        }
        log.info(msg);
    }
}
