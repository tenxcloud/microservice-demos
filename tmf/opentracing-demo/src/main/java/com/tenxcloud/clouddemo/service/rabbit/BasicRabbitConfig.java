package com.tenxcloud.clouddemo.service.rabbit;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:05
 **/
@Configuration
public class BasicRabbitConfig {
    @Bean
    public Queue basic() {
        return new Queue(RabbitService.BASIC_QUEUE);
    }
}
