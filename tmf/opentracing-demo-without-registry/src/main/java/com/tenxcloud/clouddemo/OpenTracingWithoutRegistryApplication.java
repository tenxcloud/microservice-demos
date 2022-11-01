/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2018 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.clouddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangdalei
 * @Date 2020-08-12 16:37
 **/
@SpringBootApplication/*(exclude = {DataSourceAutoConfiguration.class, KafkaAutoConfiguration.class, RabbitAutoConfiguration.class,
        RedisAutoConfiguration.class, KafkaAutoConfiguration.class})*/
public class OpenTracingWithoutRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenTracingWithoutRegistryApplication.class, args);
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
