/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */

package com.tenxcloud.dubbo.demo.api;

public interface RocketMQService {
    default void sendMsg(String topic, String msg){

    }
}
