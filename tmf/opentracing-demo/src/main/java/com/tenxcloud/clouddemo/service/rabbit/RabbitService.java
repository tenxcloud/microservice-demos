package com.tenxcloud.clouddemo.service.rabbit;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:04
 **/
public interface RabbitService {
    String BASIC = "basic";
    String DIRECT = "direct";
    String FANOUT = "fanout";
    String TOPIC = "topic";
    String BASIC_QUEUE = "basic_queue";
    String DIRECT_QUEUE = "direct_queue";
    String FANOUT_QUEUE = "fanout_queue";
    String TOPIC_QUEUE = "topic_queue";
    String BASIC_EXCHANGE = "(AMQP default)";
    String DIRECT_EXCHANGE = "direct_exchange";
    String FANOUT_EXCHANGE = "fanout_exchange";
    String TOPIC_EXCHANGE = "topic_exchange";

    String MSG = "send msg: %s, id: %s, exchange: %s, queue: %s";
    String send();
    String msg();
}
