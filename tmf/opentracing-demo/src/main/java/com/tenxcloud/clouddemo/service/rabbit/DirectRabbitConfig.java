package com.tenxcloud.clouddemo.service.rabbit;


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
    public Queue direct() {
        return new Queue(RabbitService.DIRECT_QUEUE);
    }


    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(RabbitService.DIRECT_EXCHANGE);
    }

    @Bean
    Binding bindingDirectExchangeMessage(Queue direct, DirectExchange directExchange) {
        return BindingBuilder.bind(direct).to(directExchange).with(RabbitService.DIRECT_QUEUE);
    }
}
