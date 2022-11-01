package com.tenxcloud.clouddemo.controller;

import com.tenxcloud.clouddemo.service.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  zhangdalei
 * @Date 2020-08-12 16:37
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    RedisService redisService;
    @GetMapping("/set")
    public void setKV(@RequestParam String k, @RequestParam String v) {
        redisService.setValue(k, v);
    }

    @GetMapping("/get/{k}")
    public Object getKV(@PathVariable String k) {
        return redisService.getValue(k);
    }
}
