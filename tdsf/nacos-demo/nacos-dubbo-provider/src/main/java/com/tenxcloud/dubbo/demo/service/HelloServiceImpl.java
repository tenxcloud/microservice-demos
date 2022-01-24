package com.tenxcloud.dubbo.demo.service;

import api.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

/**
 * @author zhangshuo
 */
@Slf4j
@DubboService(version = "${demo.service.version}",group = "${demo.service.group}")
public class HelloServiceImpl implements HelloService {

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping("/sayHello")
    @Override
    public String sayHello(String msg) {
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

    static int errorCount = 0;
    static int deployCount = 0;
    @Override
    public String slowCall(String msg, Long interval) {
        RpcContext rpcContext = RpcContext.getContext();
        System.out.println(rpcContext.getAttachments());
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            errorCount++;
            throw new RuntimeException("mock exception :" + msg);
        }
        if (interval > 0) {
            try {
                deployCount++;
                log.info("执行等待中。。。。！");
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
}
