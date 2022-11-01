package com.tenxcloud.clouddemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: wuhuhu
 * @date: 2021/4/20 11:31
 */
@Slf4j
@RestController
@RequestMapping("/complex/")
public class SimpleController {

    static final String EFAULT_ENV_NAME = "MS_RESULT";

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
        try {
            Thread.sleep(rt);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "i've sleep for " + rt + " with env: " + System.getenv(envName);
    }

    @GetMapping("/code/{statusCode}")
    public ResponseEntity<String> statusCode(@PathVariable int statusCode, @RequestParam(required = false, defaultValue = EFAULT_ENV_NAME) String envName) {
        if (HttpStatus.OK.value() == statusCode) {
            return new ResponseEntity<>("hello: " + System.getenv(envName), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.valueOf(statusCode));
    }
}
