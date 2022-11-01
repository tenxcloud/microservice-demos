package com.tenxcloud.clouddemo.entity;

import lombok.Data;

/**
 * alarm request .
 *
 * @author bing.zheng
 * @date 2022/7/7
 */
@Data
public class AlarmRequest {

    private AlarmMessage alarmMessage;

    private String message;

}
