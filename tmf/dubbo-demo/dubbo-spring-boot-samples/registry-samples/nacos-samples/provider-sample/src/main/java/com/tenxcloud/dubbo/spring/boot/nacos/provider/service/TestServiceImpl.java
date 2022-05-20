/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tenxcloud.dubbo.spring.boot.nacos.provider.service;

import com.tenxcloud.dubbo.demo.api.DemoService;
import com.tenxcloud.dubbo.demo.api.TestService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;

/**
 * Default {@link DemoService}
 *
 * @see DemoService
 * @since 2.7.0
 */
@RefreshScope
@DubboService(version = "${demo.service.version}")
public class TestServiceImpl implements TestService {
    @Value(value = "${useLocalCache:false}")
    private boolean useLocalCache;

    @Override
    public String hello(String msg) {
        HttpStatus[] codes = HttpStatus.values();
        HttpStatus code;
        for (int i = 0; i < codes.length ; i++) {
            code = codes[i];
            if (code.isError() && String.valueOf(code.value()).equals(msg)){
                throw new RpcException(msg);
            }
        }
        String r = System.getenv(msg.toUpperCase());
        return StringUtils.isNotBlank(r) ? r : msg;
    }

    @Override
    public boolean getConfig() {
        return useLocalCache;
    }

}