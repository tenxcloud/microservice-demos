package com.tenxcloud.dubbo.spring.boot.nacos.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo
public class ProviderSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProviderSampleApplication.class, args);
	}

}
