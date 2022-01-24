package com.tenxcloud.zookeeper.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangshuo
 */
@SpringBootApplication
@EnableDubbo
public class ZookeeperProvider {
    public static void main(String[] args) {
        SpringApplication.run(ZookeeperProvider.class, args);
    }
}
