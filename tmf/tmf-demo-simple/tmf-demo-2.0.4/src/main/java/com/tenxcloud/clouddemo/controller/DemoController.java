package com.tenxcloud.clouddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author zengbang
 * @since 2020/12/30 15:20
 */
@Slf4j
@RestController
@RequestMapping(value = "demo")
public class DemoController {
    private static int second = 0;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RestTemplate balancedRestTemplate;

    @GetMapping(value = "/testip")
    public String testip(@RequestParam("ip") String ip) {
        HttpEntity entity = new HttpEntity<>(null, null);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange("http://" + ip + ":8401/getpodinfo", HttpMethod.GET, entity, String.class);
            System.out.println("有人让我给" + ip + "ip发消息，我发了！");
            return exchange.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/testservicename")
    public String testservicename(@RequestParam("servicename") String servicename) {
        HttpEntity entity = new HttpEntity<>(null, null);
        try {
            ResponseEntity<String> exchange = balancedRestTemplate.exchange("http://" + servicename + "/getpodinfo", HttpMethod.GET, entity, String.class);
            System.out.println("有人让我给" + servicename + "服务发消息，我发了！");
            return exchange.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/testipRT")
    public String testipRT(String ip,String time) {
        HttpEntity entity = new HttpEntity<>(null, null);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange("http://" + ip + ":8401/testDegradeByRT?time="+time, HttpMethod.GET, entity, String.class);
            System.out.println("有人让我给" + ip + "ip发消息，我发了！");
            return exchange.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/testservicenameRT")
    public String testservicenameRT(String servicename,String time) {
        HttpEntity entity = new HttpEntity<>(null, null);
        try {
            ResponseEntity<String> exchange = balancedRestTemplate.exchange("http://" + servicename + ":8401/testDegradeByRT?time="+time, HttpMethod.GET, entity, String.class);
            System.out.println("有人让我给" + servicename + "服务发消息，我发了！");
            return exchange.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/testipEX")
    public String testipRT(String ip,boolean exe) {
        HttpEntity entity = new HttpEntity<>(null, null);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange("http://" + ip + ":8401/testDegradeByException?exe="+exe, HttpMethod.GET, entity, String.class);
            System.out.println("有人让我给" + ip + "ip发消息，我发了！");
            return exchange.getBody();
        } catch (Exception e) {
            return e.getMessage();                                                       
        }
    }

    @GetMapping(value = "/testservicenameEX")
    public String testservicenameRT(String servicename,boolean exe) {
        HttpEntity entity = new HttpEntity<>(null, null);
        try {
            ResponseEntity<String> exchange = balancedRestTemplate.exchange("http://" + servicename + ":8401/testDegradeByException?exe="+exe, HttpMethod.GET, entity, String.class);
            System.out.println("有人让我给" + servicename + "服务发消息，我发了！");
            return exchange.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/call/rest")
    public String callRest(@RequestParam("svcName") String svcName, @RequestParam("path") String path) {
        log.info("receive request and call svcName: {}, path: {}", svcName, path);
        return restTemplate.getForObject(String.format("http://%s/%s", svcName, path), String.class);
    }
}
