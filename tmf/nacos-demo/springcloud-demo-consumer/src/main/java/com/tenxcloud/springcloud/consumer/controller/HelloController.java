package com.tenxcloud.springcloud.consumer.controller;

import com.tenxcloud.springcloud.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangshixiong
 * @date 2020-12-15 5:34 下午
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String msg) {
        return helloService.hello(msg);
    }

    @GetMapping("/slow-call")
    public String sayHello(@RequestParam String msg, @RequestParam Long interval) {
        return helloService.slowCall(msg, interval);
    }
}
