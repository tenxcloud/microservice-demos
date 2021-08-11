/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.eureka.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangshixiong
 * @date 2021-07-06 12:58 上午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class EurekaDemoProvider {
    public static void main(String[] args) {
        SpringApplication.run(EurekaDemoProvider.class, args);
    }
}
