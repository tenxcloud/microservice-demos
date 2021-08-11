package com.tenxcloud.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author wangshixiong
 * @date 2020-12-15 8:13 下午
 */
@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudDemoProvider {
    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDemoProvider.class,args);
    }
}
