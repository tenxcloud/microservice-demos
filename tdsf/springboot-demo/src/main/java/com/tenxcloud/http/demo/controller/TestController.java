package com.tenxcloud.http.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author zhangshuo
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/sayHello")
    public String hello(String msg) {
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            errorCount ++;
            throw new RuntimeException("mock exception :" + msg);
        }
        log.info("sayHello Number of previous calls of error: {}", errorCount);
        int error = errorCount;
        errorCount = 0;
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s], Provider [%s] : Hello, %s, time: %s, Number of previous calls: %s ",
                System.getenv("MESH_SVC_NAME"), System.getenv("MESH_SVC_VERSION"),
                applicationName, msg, new Date(), error);
    }

    @GetMapping("/sayHello2")
    public String hello2(String msg) {
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            throw new RuntimeException("mock exception :" + msg);
        }
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s] : Hello, %s, time: %s ", System.getenv("MESH_SVC_NAME"), System.getenv("MESH_SVC_VERSION"), msg, new Date());
    }
    static int errorCount = 0;
    static int deployCount = 0;



    @GetMapping("/slow-call")
    public String slowCall(String msg, long interval) {
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            errorCount++;
            throw new RuntimeException("mock exception :" + msg);
        }
        if (interval > 0) {
            try {
                log.info("执行等待中。。。。！");
                deployCount ++;
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                throw new RuntimeException("test time interval mock exception :" + msg + " 间隔：" + interval);
            }
        }
        log.info("slow-call Number of previous calls of error: {}", errorCount);
        log.info("slow-call Number of previous calls of deploy: {}", deployCount);
        int error = errorCount;
        errorCount = 0;
        int deploy = deployCount;
        deployCount = 0;
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s], Provider [%s] : Hello, %s, time: %s Number of previous calls: %s, deploy counts: %s ",
                System.getenv("MESH_SVC_NAME"), System.getenv("MESH_SVC_VERSION"), applicationName,
                msg, new Date(), error, deploy);
    }

    @Value("${spring.application.name}")
    private String applicationName;

}

