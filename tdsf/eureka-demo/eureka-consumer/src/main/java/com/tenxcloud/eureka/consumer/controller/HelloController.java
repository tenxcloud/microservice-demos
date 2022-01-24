/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.eureka.consumer.controller;
import com.tenxcloud.eureka.consumer.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangshuo
 */
@RestController
@Slf4j
public class HelloController {

    private HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String msg) {
        log.info(msg);
        return helloService.hello(msg);
    }

    @GetMapping("/sayHello2")
    public String sayHello2(@RequestParam String msg) {
        log.info(msg);
        return helloService.hello2(msg);
    }

    @GetMapping("/slow-call")
    public String slowCall(@RequestParam String msg,@RequestParam(value = "interval") long interval) {
        log.info("msg: {}, intervalL: {}",msg, interval);
        return helloService.slowCall(msg, interval);
    }
}
