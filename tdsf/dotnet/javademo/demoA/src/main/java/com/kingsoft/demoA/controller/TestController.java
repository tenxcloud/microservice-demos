package com.kingsoft.demoA.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestController {
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${server.name.serverB}")
    private String serverBName;

    @Value("${server.name.netServerAAddr}")
    private String netServerAAddr;

    @Value("${server.name.netServerBAddr}")
    private String netServerBAddr;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("hello")
    public String getHello(){
        return "java hello word a ..." + System.currentTimeMillis();
    }


    @GetMapping("/test/a/to/b")
    public String testatob(){
        return  restTemplate.getForObject("http://"+serverBName+"/demob/hello", String.class);
    }


    @GetMapping("/test/net/demoa")
    public String testNetDemoa(){
        return  "Java A服务调用.net A服务，返回结果：" + restTemplate.getForObject("http://"+netServerAAddr+"/demoa/test", String.class);
    }

    @GetMapping("/test/net/demob")
    public String testNetDemob(){
        return  "Java A服务调用.net B服务，返回结果：" + restTemplate.getForObject("http://"+netServerBAddr+"/demob/test", String.class);
    }

}
