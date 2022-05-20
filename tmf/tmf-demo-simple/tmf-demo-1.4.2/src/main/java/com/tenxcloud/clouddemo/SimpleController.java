package com.tenxcloud.clouddemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: wuhuhu
 * @date: 2021/4/20 11:31
 */
@Slf4j
@RestController
@RefreshScope
public class SimpleController {

    static final String EFAULT_ENV_NAME = "MS_RESULT";


    @Value("${project.version}")
    String projectVersion;

    @GetMapping("/version")
    public String projectVersion(HttpServletRequest request) {
        log.info("i've received request, remote ip: " + request.getRemoteAddr());
        return projectVersion;
    }

    @Autowired
    RestTemplate balancedRestTemplate;

    @GetMapping("/hello")
    public String hello(@RequestParam(required = false, defaultValue = EFAULT_ENV_NAME) String envName, HttpServletRequest request) {
        log.info("i've received request, remote ip: " + request.getRemoteAddr());
        return String.format("hello %s, %s env value is : %s", request.getRemoteAddr(), envName, System.getenv(envName));
    }

    @GetMapping("/call/{svcName}/**")
    public String call(@PathVariable String svcName, HttpServletRequest request,
                       @RequestParam(required = false, defaultValue = EFAULT_ENV_NAME) String envName) {
        final String path = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        final String bestMatchingPattern = request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE).toString();
        String arguments = new AntPathMatcher().extractPathWithinPattern(bestMatchingPattern, path);
        return balancedRestTemplate.getForObject(String.format("http://%s/%s?envName=%s", svcName, arguments, envName), String.class);
    }

    @GetMapping("/env/{envName}")
    public String env(@PathVariable String envName) {
        return "hello env: " + System.getenv(envName);
    }

    @GetMapping("/sleep/{rt}")
    public String sleep(@PathVariable long rt,
                        @RequestParam(required = false, defaultValue = EFAULT_ENV_NAME) String envName) {
        sleep(rt);
        return "i've sleep for " + rt + " with env: " + System.getenv(envName);
    }

    @GetMapping("/code/{statusCode}")
    public ResponseEntity<String> statusCode(@PathVariable int statusCode, @RequestParam(required = false, defaultValue = EFAULT_ENV_NAME) String envName) {
        if (HttpStatus.OK.value() == statusCode) {
            return new ResponseEntity<>("hello: " + System.getenv(envName), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.valueOf(statusCode));
    }

    @GetMapping("/headers")
    public String receivedHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        List<String> headerValueList = new ArrayList<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String value = request.getHeader(headerName);
            headerValueList.add(headerName + ":" + value);
        }
        log.info("received headers: " + headerValueList);
        return headerValueList.toString();
    }

    Map<String, AtomicInteger> retryMap = new ConcurrentHashMap<>();

    @GetMapping("/retry/{key}/increment/{sleep}")
    public Integer inc(@PathVariable String key, @PathVariable long sleep) {
        retryMap.putIfAbsent(key, new AtomicInteger(0));
        log.info("received request and current value is: " + retryMap.get(key).get() + " and sleep: " + sleep);
        sleep(sleep);
        return retryMap.get(key).incrementAndGet();
    }

    @GetMapping("/retry/{key}/decrement/{sleep}")
    public Integer dec(@PathVariable String key, @PathVariable long sleep) {
        retryMap.putIfAbsent(key, new AtomicInteger(0));
        log.info("received request and current value is: " + retryMap.get(key).get() + " and sleep: " + sleep);
        sleep(sleep);
        return retryMap.get(key).decrementAndGet();
    }

    private void sleep(long duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
