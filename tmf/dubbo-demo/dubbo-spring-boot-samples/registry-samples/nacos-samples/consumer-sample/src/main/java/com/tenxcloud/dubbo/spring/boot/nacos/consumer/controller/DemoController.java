/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.dubbo.spring.boot.nacos.consumer.controller;

import com.tenxcloud.dubbo.demo.api.DemoService;
import com.tenxcloud.dubbo.demo.api.HelloService;
import com.tenxcloud.dubbo.demo.api.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author: zhangdalei
 * @date: 2020-10-22 22:59
 **/
@RestController
@RequestMapping("/demo")
public class DemoController {
    @DubboReference(version = "${demo.service.version}")
    private DemoService demoService;
    @DubboReference(version = "${demo.service.version}")
    private HelloService helloService;
    @DubboReference(version = "${demo.service.version}")
    private TestService testService;


    @GetMapping(value = "/env/{msg}")
    public String getEnv(@PathVariable String msg) {
        switch (random()){
            case 1:
                return demoService.hello(msg);
            case 2:
                return testService.hello(msg);
            default:
                return helloService.hello(msg);
        }
    }

    @GetMapping(value = "/config")
    public boolean get() {
        switch (random()){
            case 1:
                return demoService.getConfig();
            case 2:
                return testService.getConfig();
            default:
                return helloService.getConfig();
        }
    }

    private int random(){
        Random random = new Random();
        return random.nextInt(3);
    }
}
