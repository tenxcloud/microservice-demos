package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.rabbit;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:05
 **/
@Configuration
public class BasicRabbitConfig {

    @Bean
    public Queue basicA() {
        return new Queue("tmf_basic_A");
    }

    @Bean
    public Queue basicB() {
        return new Queue("tmf_basic_B");
    }

    @Bean
    public Queue basicC() {
        return new Queue("tmf_basic_C");
    }

}
