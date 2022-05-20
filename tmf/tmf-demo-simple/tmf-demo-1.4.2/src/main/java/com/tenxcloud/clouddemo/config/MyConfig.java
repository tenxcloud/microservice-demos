package com.tenxcloud.clouddemo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean(value = "restTemplate")
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean(value = "balancedRestTemplate")
    @LoadBalanced
    RestTemplate balancedRestTemplate(RestTemplateBuilder builder) {
        RestTemplate template = builder.build();
        return template;
    }
}
