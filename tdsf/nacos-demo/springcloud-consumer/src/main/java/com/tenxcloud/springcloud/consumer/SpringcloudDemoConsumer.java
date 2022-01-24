package com.tenxcloud.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author zhangshuo
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SpringcloudDemoConsumer {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDemoConsumer.class, args);
    }
}
