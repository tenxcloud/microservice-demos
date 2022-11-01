package com.tenxcloud.clouddemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SimpleApplication {
    public static void main( String[] args ) {
        SpringApplication.run(SimpleApplication.class, args);
        log.info("==================tmf-springCloud-demo-2.0.4 start!==================");
    }
}
