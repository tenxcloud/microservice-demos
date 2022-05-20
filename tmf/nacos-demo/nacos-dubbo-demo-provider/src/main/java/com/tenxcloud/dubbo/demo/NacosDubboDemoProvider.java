package com.tenxcloud.dubbo.demo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangshixiong
 * @date 2020-12-15 2:52 下午
 */
@SpringBootApplication
@EnableDubbo
public class NacosDubboDemoProvider {
    public static void main(String[] args) {
        SpringApplication.run(NacosDubboDemoProvider.class, args);
    }
}
