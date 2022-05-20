package com.tenxcloud.clouddemo.controller;

import com.tenxcloud.clouddemo.service.rabbit.producer.BasicSender;
import com.tenxcloud.clouddemo.service.rabbit.producer.DirectSender;
import com.tenxcloud.clouddemo.service.rabbit.producer.FanoutSender;
import com.tenxcloud.clouddemo.service.rabbit.producer.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author  zhangdalei
 * @Date 2020-08-12 16:37
 **/
@RestController
@RequestMapping("/rabbit")
public class RabbitController {
    @Autowired
    private BasicSender basic;
    @Autowired
    private FanoutSender fanout;
    @Autowired
    private TopicSender topic;
    @Autowired
    private DirectSender direct;

    @GetMapping("/send/{type}")
    public String send(@PathVariable String type) {
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
        return "发送成功成功！！！";
    }
}
