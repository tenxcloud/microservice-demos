package com.tenxcloud.springcloud.consumer.controller;

import com.tenxcloud.springcloud.consumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangshuo
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String msg) {
        return helloService.hello(msg);
    }
    @GetMapping("/sayHello2")
    public String sayHello2(@RequestParam String msg) {
        return helloService.hello2(msg);
    }

    @GetMapping("/slow-call")
    public String sayHello(@RequestParam String msg, @RequestParam Long interval) {
        return helloService.slowCall(msg, interval);
    }
}
