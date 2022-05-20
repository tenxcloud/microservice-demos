/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2018 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.clouddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author  zhangdalei
 * @Date 2020-08-12 16:37
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class OpenTracingApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenTracingApplication.class,args);
    }
    @Bean
    @LoadBalanced
    RestTemplate balancedRestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }
}
