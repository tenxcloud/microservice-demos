package com.tenxcloud.dubbo.demo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhangshuo
 */
@SpringBootApplication
@EnableDubbo
public class NacosDubboDemoProvider {
    public static void main(String[] args) {
        SpringApplication.run(NacosDubboDemoProvider.class, args);
    }
}
