package com.tenxcloud.clouddemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangyongchao
 * @date 2020/12/22
 * @apiNote
 */
@RestController
@RequestMapping("testRoute")
public class RouteTestController {
    
    private static int num = 0;
    
    @GetMapping
    public String testRoute() {
        return "访问成功！";
    }
    
    @GetMapping("testSensitiveHeaders")
    public Map<String, String> testSensitiveHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> map = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            map.put(header, request.getHeader(header));
        }
        return map;
    }
    
    @GetMapping("testRoutingPrefix")
    public Map<String, String> testRoutingPrefix(HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        String contextPath = request.getContextPath();
        String pathInfo = request.getPathInfo();
        String requestURI = request.getRequestURI();
        StringBuffer requestURL = request.getRequestURL();
        String servletPath = request.getServletPath();
        String localAddr = request.getLocalAddr();
        Map<String, String> map = new HashMap<>();
        map.put("remoteAddr", remoteAddr);
        map.put("contextPath", contextPath);
        map.put("pathInfo", pathInfo);
        map.put("requestURI", requestURI);
        map.put("requestURL", requestURL.toString());
        map.put("servletPath", servletPath);
        map.put("localAddr", localAddr);
        return map;
    }
    
    @GetMapping("/testRetry")
    public void testRetry() {
        num++;
        int i = 1/0;
    }
    
    @GetMapping("/failNum")
    public String getFailNum() {
        return "失败次数：" + num;
    }
    
    @GetMapping("/responseTime")
    public String responseTime() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "接口在1秒后返回！";
    }
}
