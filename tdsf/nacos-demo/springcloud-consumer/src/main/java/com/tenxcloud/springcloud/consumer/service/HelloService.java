package com.tenxcloud.springcloud.consumer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangshuo
 */
@FeignClient(value = "springcloud-provider")
public interface HelloService {

    @GetMapping(value = "hello")
    String hello(@RequestParam(value = "msg") String msg);
    @GetMapping(value = "hello2")
    String hello2(@RequestParam(value = "msg") String msg);
    @GetMapping(value = "slow-call")
    String slowCall(@RequestParam(value = "msg") String msg, @RequestParam(value = "interval") Long interval);

}
