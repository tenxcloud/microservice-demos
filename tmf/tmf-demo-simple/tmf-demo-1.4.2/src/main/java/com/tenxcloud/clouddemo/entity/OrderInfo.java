/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.clouddemo.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @Description:
 * @Author: wudongliang
 * @Date: 2021/3/3 17:44
 */
@Data
@ToString
public class OrderInfo {

    private String orderId;

    private String commodityName;

    private String amount;
}
