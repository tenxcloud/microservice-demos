package com.tenxcloud.springcloud.provider.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author wangshixiong
 * @date 2020-12-15 6:08 下午
 */
@RestController
@Slf4j
public class HelloController {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("hello")
    public String hello(@RequestParam(value = "msg") String msg) {
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            throw new RuntimeException("mock exception :" + msg);
        }
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s], Provider [%s] : Hello, %s, time: %s ", applicationName, System.getenv("MESH_SVC_VERSION"), applicationName, msg, new Date());

    }

    @GetMapping("slow-call")
    public String hello(@RequestParam(value = "msg") String msg, @RequestParam(value = "interval") Long interval) {
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            throw new RuntimeException("mock exception :" + msg);
        }
        if (interval > 0) {
            try {
                log.info("执行等待中。。。。！");
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException("test time interval mock exception :" + msg + " 间隔：" + interval);
            }
        }
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s], Provider [%s] : Hello, %s, time: %s ", applicationName, System.getenv("MESH_SVC_VERSION"), applicationName, msg, new Date());

    }
}