package com.tenxcloud.clouddemo.service.rocketmq.listener;


import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RocketMQMessageListener(topic = "topic-d", selectorExpression = "*", enableMsgTrace = false, consumerGroup = "consumer_group_topic_d")
public class ConsumerListenerTopicD implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt message) {
        log.info("消费主题: {},消息: {}", "topic-d", new String(message.getBody()));
    }
}
