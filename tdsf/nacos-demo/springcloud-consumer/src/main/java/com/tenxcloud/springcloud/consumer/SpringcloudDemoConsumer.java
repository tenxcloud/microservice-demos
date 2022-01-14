package com.tenxcloud.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangshixiong
 * @date 2020-12-15 8:06 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringcloudDemoConsumer {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDemoConsumer.class, args);
    }
}
