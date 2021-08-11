package com.tenxcloud.zookeeper.provider.service;

import api.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author wangshixiong
 * @date 2020-12-15 2:55 下午
 */
@Service(version = "${demo.service.version}", group = "${demo.service.group}")
@Slf4j
public class TestServiceImpl implements TestService {
    @Value("${spring.application.name}")
    private String applicationName;

    @Override
    public String test(String msg) {
        RpcContext rpcContext = RpcContext.getContext();
        System.out.println(rpcContext.getAttachments());
        log.info(msg);
        if ("500".equals(msg) || "503".equals(msg) || "400".equals(msg)) {
            throw new RuntimeException("mock exception :" + msg);
        }
        return String.format("MESH_SVC_NAME:[%s], MESH_SVC_VERSION:[%s], Provider [%s] : Hello, %s, time: %s ", System.getenv("MESH_SVC_NAME"), System.getenv("MESH_SVC_VERSION"), applicationName, msg, new Date());
    }
}
