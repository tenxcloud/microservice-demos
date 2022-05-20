package com.tenxcloud.clouddemo.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("kafka")
public class KafkaController {
    private final KafkaTemplate<String, Object> template;

    public KafkaController(KafkaTemplate<String, Object> template) {
        this.template = template;
    }

    @GetMapping("/send/{topic}")
    public String send(@PathVariable String topic) throws InterruptedException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = dateFormat.format(new Date());
        switch (topic) {
            case "a":
                template.send("kafka-topic-a", time);
                return "send to topic-" + topic + " success";
            case "b":
                template.send("kafka-topic-b", time);
                return "send to topic-" + topic + " success";
            case "c":
                template.send("kafka-topic-c", time);
                return "send to topic-" + topic + " success";
            case "d":
                template.send("kafka-topic-d", time);
                return "send to topic-" + topic + " success";
            case "e":
                template.send("kafka-topic-e", time);
                return "send to topic-" + topic + " success";
        }
        return "not match topic";
    }

}
