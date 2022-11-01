package com.tenxcloud.clouddemo.controller;

import com.alibaba.fastjson.JSON;
import com.tenxcloud.clouddemo.entity.AlarmRequest;
import com.tenxcloud.clouddemo.entity.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

/**
 * Receiver alarm msg .
 *
 * @author bing.zheng
 * @date 2022/7/7
 */
@RestController
@Slf4j
public class ReceiverAlarmMsgController {

    @PostMapping("/receiver/alarm")
    public CommonResult receiver(@RequestBody AlarmRequest alarmRequest) {
        if (Objects.isNull(alarmRequest)) {
            log.info("接收数据为空！");
            return new CommonResult(400, "无消息");
        }
        log.info("收到消息：message：{}, alarmMessage: {}", alarmRequest.getMessage(), JSON.toJSONString(alarmRequest.getAlarmMessage()));
        return new CommonResult(200, "接收到消息");
    }


}
