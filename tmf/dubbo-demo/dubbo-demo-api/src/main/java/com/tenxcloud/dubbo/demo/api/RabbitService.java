package com.tenxcloud.dubbo.demo.api;

/**
 * @author: zhangdalei
 * @date: 2020-08-13 14:04
 **/
public interface RabbitService {
    default void send() {

    }

    default void send(String type) {

    };
}
