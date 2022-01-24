package com.tenxcloud.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author zhangshuo
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudDemoProvider {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDemoProvider.class,args);
    }
}
