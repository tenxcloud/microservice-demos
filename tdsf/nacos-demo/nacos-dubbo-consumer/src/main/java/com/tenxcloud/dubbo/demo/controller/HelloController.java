package com.tenxcloud.dubbo.demo.controller;

import api.HelloService;
import api.TestService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangshuo
 */
@RestController
public class HelloController {
    @DubboReference(version = "${services.hello.version}", group = "${services.hello.group}")
    private HelloService helloService;

    @DubboReference(version = "${services.test.version}", group = "${services.test.group}")
    private TestService testService;

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam String msg) {
        return helloService.sayHello(msg);
    }

    @GetMapping("/test")
    public String test(@RequestParam String msg) {
        return testService.test(msg);
    }

    @GetMapping("/slow-call")
    public String sayHello(@RequestParam String msg, @RequestParam Long interval) {
        return helloService.slowCall(msg, interval);
    }

    @GetMapping("/test/slow-call")
    public String testSlow(@RequestParam String msg, @RequestParam Long interval) {
        return testService.slowCall(msg, interval);
    }
}
