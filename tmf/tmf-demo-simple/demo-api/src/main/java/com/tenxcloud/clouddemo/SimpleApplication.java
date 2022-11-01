package com.tenxcloud.clouddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = "com.tenxcloud.clouddemo")
public class SimpleApplication {
    public static void main( String[] args ) {
        SpringApplication.run(SimpleApplication.class, args);
    }
}
