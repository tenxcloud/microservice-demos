package com.tenxcloud.clouddemo.service.rabbit;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:05
 **/
@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue topic() {
        return new Queue(RabbitService.TOPIC_QUEUE);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(RabbitService.TOPIC_EXCHANGE);
    }

    @Bean
    Binding bindingTopicExchangeMessageA(Queue topic, TopicExchange topicExchange) {
        return BindingBuilder.bind(topic).to(topicExchange).with(RabbitService.TOPIC_QUEUE);
    }
}
