/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2018 TenxCloud. All Rights Reserved.
 */

package com.tenxcloud.clouddemo.feign.demo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author zhangdalei
 * @date 10:49 上午 2020/9/23
 * @return
 */
@FeignClient(value = "opentracing-demo-b", path = "demo")
public interface DemoBFeignClient {
    @GetMapping("/env/{msg}")
    String getEnv(@PathVariable String msg);
    @GetMapping("/refresh/value")
    String refresh();
}
