/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2019 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.clouddemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.tenxcloud.clouddemo.db.dto.SnapshotApi;
import com.tenxcloud.clouddemo.db.dto.TenxCluster;
import com.tenxcloud.clouddemo.db.repository.SnapshotRepository;
import com.tenxcloud.clouddemo.db.repository.TenxClusterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: wuhuhu
 * @date: 2021/4/20 14:36
 */
@RestController
@Slf4j
public class SwTraceQueryController {

    @Autowired
    RestTemplate balancedRestTemplate;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    TenxClusterRepository clusterRepository;
    @Autowired
    SnapshotRepository snapshotRepository;

    @GetMapping("/hello")
    public String hello(){
        log.info("receive request and return hello");
        return "hello";
    }

    @GetMapping("/env/{envName}")
    public String env(@PathVariable String envName){
        log.info("receive request and return env");
        return String.format("hello: %s",  System.getenv(envName));
    }

    /**
     * @author:XingWL
     * @description:查询数据库
     * @date: 2021/1/11 2:18 下午
     */
    @GetMapping("/apis/{id}")
    public SnapshotApi queryApiInfo(@PathVariable Long id){
        return snapshotRepository.findSnapshotApiById(id);
    }

    /**
     * @author:XingWL
     * @description:
     * @date: 2021/1/11 6:42 下午
     */
    @GetMapping("/cache/apis/{apiId}")
    public String getDataFromRedis(@PathVariable Long apiId){
        String result;
        if(Optional.ofNullable(redisTemplate.opsForValue().get(Long.toString(apiId))).isPresent()){
            result = redisTemplate.opsForValue().get(Long.toString(apiId)).toString();
        }else {
            result = JSONObject.toJSONString(snapshotRepository.findSnapshotApiById(apiId));
            redisTemplate.opsForValue().set(Long.toString(apiId), snapshotRepository.findSnapshotApiById(apiId));
        }
        return result;
    }

    @GetMapping("/clusters/{clusterId}")
    public TenxCluster getClusterInfo(@PathVariable String clusterId){
        if(redisTemplate.hasKey(clusterId)){
            return JSONObject.parseObject(redisTemplate.opsForValue().get(clusterId).toString(), TenxCluster.class);
        }else {
            TenxCluster tenxCluster = clusterRepository.findById(clusterId).get();
            if(Optional.ofNullable(tenxCluster).isPresent()){
                redisTemplate.opsForValue().setIfAbsent(clusterId, tenxCluster, 60000, TimeUnit.MILLISECONDS);
            }
            return tenxCluster;
        }
    }
    @GetMapping("/call/{svcName}/{path}")
    public String call(@PathVariable String svcName, @PathVariable String path) {
        log.info("receive request and call svcName: {}, path: {}", svcName, path);
        return balancedRestTemplate.getForObject(String.format("http://%s/%s", svcName, path), String.class);
    }

    @GetMapping("/call/rest/{svcName}/{path}")
    public String callRest(@PathVariable String svcName, @PathVariable String path) {
        log.info("receive request and call svcName: {}, path: {}", svcName, path);
        return restTemplate.getForObject(String.format("http://%s/%s", svcName, path), String.class);
    }

}
