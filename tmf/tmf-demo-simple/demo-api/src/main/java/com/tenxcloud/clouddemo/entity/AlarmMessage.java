package com.tenxcloud.clouddemo.entity;

import lombok.Data;

import java.util.List;

/**
 * alarm message from skywalking .
 *
 * @author bing.zheng
 * @date 2022/7/7
 */
@Data
public class AlarmMessage {

    private int scopeId;

    private String scope;

    private String name;

    private String id0;

    private String id1;

    private String ruleName;

    private String alarmMessage;

    private List<Tag> tags;

    private long startTime;
}
