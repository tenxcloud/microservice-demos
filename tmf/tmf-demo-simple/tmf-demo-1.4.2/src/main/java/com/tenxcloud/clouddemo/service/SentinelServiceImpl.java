package com.tenxcloud.clouddemo.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zengbang
 * @since 2020/12/17 15:05
 */
@Service
public class SentinelServiceImpl {

    @SentinelResource("zengbang")
    public String show() {
        System.out.println("有人点击到我了！" + new Date().toLocaleString());
        return "OK";
    }
}
