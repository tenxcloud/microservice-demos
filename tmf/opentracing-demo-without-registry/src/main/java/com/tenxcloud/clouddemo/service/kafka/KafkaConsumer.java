package com.tenxcloud.clouddemo.service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = {"kafka-topic-a"})
    public void listenMsgTopicA(String data) {
        log.info("消费 kafka topic: {} 消息为：{}", "kafka-topic-a", data);
    }

    @KafkaListener(topics = {"kafka-topic-b"})
    public void listenMsgTopicB(String data) {
        log.info("消费 kafka topic: {} 消息为：{}", "kafka-topic-b", data);
    }

    @KafkaListener(topics = {"kafka-topic-c"})
    public void listenMsgTopicC(String data) {
        log.info("消费 kafka topic: {} 消息为：{}", "kafka-topic-c", data);
    }

    @KafkaListener(topics = {"kafka-topic-d"})
    public void listenMsgTopicD(String data) {
        log.info("消费 kafka topic: {} 消息为：{}", "kafka-topic-d", data);
    }

    @KafkaListener(topics = {"kafka-topic-e"})
    public void listenMsgTopicE(String data) {
        log.info("消费 kafka topic: {} 消息为：{}", "kafka-topic-e", data);
    }

}
