package com.tenxcloud.dubbo.spring.boot.nacos.provider.service.rabbit.producer;

import com.tenxcloud.dubbo.demo.api.RabbitService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bzheng on 2021/6/7.
 */
@DubboService(version = "${demo.service.version}")
public class RabbitServiceImpl implements RabbitService {

    @Autowired
    private BasicSender basic;
    @Autowired
    private FanoutSender fanout;
    @Autowired
    private TopicSender topic;
    @Autowired
    private DirectSender direct;

    @Override
    public void send(String type) {
        switch (type){
            case "basic":
                basic.send();
                break;
            case "fanout":
                fanout.send();
                break;
            case "direct":
                direct.send();
            default:
                topic.send();
                break;
        }
    }
}
