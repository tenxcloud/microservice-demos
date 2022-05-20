package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.rabbit;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:05
 **/
@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue directA() {
        return new Queue("tmf_direct_A");
    }

    @Bean
    public Queue directB() {
        return new Queue("tmf_direct_B");
    }

    @Bean
    public Queue directC() {
        return new Queue("tmf_direct_C");
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("tmf_direct");
    }

    @Bean
    Binding bindingDirectExchangeMessageA(Queue directA, DirectExchange directExchange) {
        return BindingBuilder.bind(directA).to(directExchange).with("direct.a");
    }

    @Bean
    Binding bindingDirectExchangeMessageB(Queue directB, DirectExchange directExchange) {
        return BindingBuilder.bind(directB).to(directExchange).with("direct.b");
    }

    @Bean
    Binding bindingDirectExchangeMessageC(Queue directC, DirectExchange directExchange) {
        return BindingBuilder.bind(directC).to(directExchange).with("direct.c");
    }


}
