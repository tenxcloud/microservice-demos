package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.rabbit;


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
    public Queue topicA() {
        return new Queue("tmf_topic_A");
    }

    @Bean
    public Queue topicB() {
        return new Queue("tmf_topic_B");
    }

    @Bean
    public Queue topicC() {
        return new Queue("tmf_topic_C");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("tmf_topic");
    }

    @Bean
    Binding bindingTopicExchangeMessageA(Queue topicA, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicA).to(topicExchange).with("topic.a.#");
    }

    @Bean
    Binding bindingTopicExchangeMessageB(Queue topicB, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicB).to(topicExchange).with("topic.b.#");
    }

    @Bean
    Binding bindingTopicExchangeMessageC(Queue topicC, TopicExchange topicExchange) {
        return BindingBuilder.bind(topicC).to(topicExchange).with("topic.c.#");
    }

}
