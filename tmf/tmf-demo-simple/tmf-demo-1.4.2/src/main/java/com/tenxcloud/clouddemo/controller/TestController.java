package com.tenxcloud.clouddemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author zengbang
 * @since 2020/12/30 15:20
 */
@Slf4j
@RestController
@RequestMapping()
public class TestController {
    private static int second = 0;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RestTemplate balancedRestTemplate;

    @Value(value = "${spring.cloud.nacos.discovery.server-addr}")
    String nacos_addr;
    @Value(value = "${spring.cloud.nacos.discovery.namespace}")
    String nacos_namespace;
    @Value(value = "${spring.cloud.nacos.discovery.service}")
    String nacos_service;

    @Value(value = "${spring.cloud.nacos.discovery.group}")
    String nacos_group;
    @Value(value = "${spring.cloud.nacos.discovery.metadata.paas-name}")
    String paas_name;
    @Value(value = "${spring.cloud.nacos.discovery.metadata.paas-ns}")
    String paas_ns;
    @Value(value = "${spring.cloud.nacos.discovery.metadata.service-type}")
    String service_type;


//    @Value(value = "${dubbo.protocol.port}")
//    String dubbo_protocol_port;


    @GetMapping(value = "/getpodinfo")
    public String getInfo() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("nacos_addr:").append(nacos_addr).append("\r\n").append("<br/>");
        stringBuilder.append("nacos_namespace:").append(nacos_namespace).append("\r\n").append("<br/>");
        stringBuilder.append("nacos_service:").append(nacos_service).append("\r\n").append("<br/>");
        stringBuilder.append("nacos_group:").append(nacos_group).append("\r\n").append("<br/>");
        stringBuilder.append("paas_name:").append(paas_name).append("\r\n").append("<br/>");
        stringBuilder.append("paas_ns:").append(paas_ns).append("\r\n").append("<br/>");
        stringBuilder.append("service_type:").append(service_type).append("\r\n").append("<br/>");
//        stringBuilder.append("dubbo_protocol_port:").append(dubbo_protocol_port).append("\r\n").append("<br/>");
        log.info(stringBuilder.toString());
        return stringBuilder.toString();
    }

    private final static String CLUSTER_ID = "CLUSTER_ID";

    @GetMapping(value = "/answer")
    public String answer(HttpServletRequest request) throws UnknownHostException {
        String remoteIp = request.getRemoteHost();
        String cluster_id = System.getenv(CLUSTER_ID);
        log.info("demo所在的集群是:{}", cluster_id);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("time:  " + LocalDateTime.now() + "    当前ip:  " + InetAddress.getLocalHost() + "    CLUSTER_ID:  " + cluster_id + "    remoteIp:  " + remoteIp);
        log.info(stringBuilder.toString());
        return stringBuilder.toString();
    }

    @GetMapping(value = "/testbyip")
    public String testbyip(@RequestParam("ip") String ip) {
        HttpEntity entity = new HttpEntity<>(null, null);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange("http://" + ip + ":8401/answer", HttpMethod.GET, entity, String.class);
            System.out.println("有人让我给" + ip + "发消息，我发了！");
            return exchange.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping(value = "/testbysvc")
    public String testbysvc(@RequestParam("servicename") String servicename) {
        HttpEntity entity = new HttpEntity<>(null, null);
        try {
            ResponseEntity<String> exchange = balancedRestTemplate.exchange("http://" + servicename + "/answer", HttpMethod.GET, entity, String.class);
            System.out.println("有人让我给" + servicename + "发消息，我发了！");
            return exchange.getBody();
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @GetMapping(value = "/testFlow")
    public String testFlow() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("testFlow  " + now);
        return "testFlow  " + now;
    }

    @PostMapping(value = "/testFlow")
    public String testFlow1() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("testFlow  " + now);
        return "testFlow  " + now;
    }

    @GetMapping(value = "/testDegradeByRT")
    public String testDegradeByRT(int time) throws InterruptedException {
        Thread.sleep(time);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("/testDegradeByRT  " + now);
        return "/testDegradeByRT  " + now;
    }

    @GetMapping(value = "/testDegradeByException")
    public String testDegradeByException(boolean exe) {
        if (exe) {
            int i = 10 / 0;
        }
        LocalDateTime now = LocalDateTime.now();
        System.out.println("/testDegradeByException  " + now);

        return "/testDegradeByException  " + now;
    }

    @GetMapping(value = "/testSystem")
    public String testSystem() {
        System.out.println("/testSystem" + new Date().toLocaleString());
        LocalDateTime now = LocalDateTime.now();
        return "/testSystem" + now;
    }

    @GetMapping(value = "/testAuthor")
    public String testAuthor() {
        System.out.println("/testAuthor  " + new Date().toLocaleString());
        LocalDateTime now = LocalDateTime.now();
        return "/testAuthor  " + now;
    }


}
