package com.tenxcloud.clouddemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author wangyongchao
 * @date 2021/1/11
 * @apiNote
 */
@RestController
@RequestMapping("/loadbalance")
public class DynamicLoadBalanceController {
    
    @GetMapping("/test")
    public String test() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();
        return localHost.getHostAddress();
    }
}
