/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.eureka.provider.controller;

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
public class HelloController {

    @Value("${spring.application.name}")
    private String applicationName;


    public static int i = 0;
    public static int errorCount = 0;
    public static int deployCount = 0;

    @GetMapping("hello")
    public String hello(@RequestParam String msg) {
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            errorCount ++;
            throw new RuntimeException("mock exception :" + msg);
        }
        log.info("Number of previous calls of error: {}", errorCount);
        int error = errorCount;
        errorCount = 0;
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s], Provider [%s] : Hello, %s, time: %s, Number of previous calls: %s ",
                System.getenv("MESH_SVC_NAME"), System.getenv("MESH_SVC_VERSION"),
                applicationName, msg, new Date(), error);
    }
    @GetMapping("hello2")
    public String hello2(@RequestParam String msg) {
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            throw new RuntimeException("mock exception :" + msg);
        }
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s], Provider [%s] : Hello, %s, time: %s ", System.getenv("MESH_SVC_NAME"), System.getenv("MESH_SVC_VERSION"), applicationName, msg, new Date());
    }

    @GetMapping("slow-call")
    public String slowCall(@RequestParam String msg, @RequestParam(defaultValue = "0",required = false) long interval ) {
        log.info(msg);
        i ++;
        log.info("Number of Calls: {} ",i);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
//            log.info("error count: {}", ++errorCount);
            errorCount++;
            throw new RuntimeException("mock exception :" + msg);
        }
        if (interval > 0){
            try {
                deployCount ++;
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        log.info("Number of previous calls of error: {}", errorCount);
        log.info("Number of previous calls of deploy: {}", deployCount);
        int error = errorCount;
        errorCount = 0;
        int deploy = deployCount;
        deployCount = 0;
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s], Provider [%s] : Hello, %s, time: %s Number of previous calls: %s, deploy counts: %s ",
                System.getenv("MESH_SVC_NAME"), System.getenv("MESH_SVC_VERSION"), applicationName,
                msg, new Date(), error, deploy);
    }
}
