package com.tenxcloud.clouddemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication(scanBasePackages = "com.tenxcloud.clouddemo")
public class SimpleApplication {
    public static void main( String[] args ) {
        SpringApplication.run(SimpleApplication.class, args);
        log.info("==================tmf-springCloud-demo-1.4.2 start!==================");
    }
    
}
