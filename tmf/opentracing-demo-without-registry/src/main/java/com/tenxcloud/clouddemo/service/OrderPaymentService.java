/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2022 TenxCloud. All Rights Reserved.
 *
 */

package com.tenxcloud.clouddemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * @Description:
 * @Author: wudongliang
 * @Date: 2021/2/25 10:24
 */
@Service
public class OrderPaymentService {

    @Autowired
    private RestTemplate restTemplate;

    public String orderPayment(String orderId, String amount, String commodityName) {
        return "test non-static method success,orderId:" + orderId + ",amount:" + amount + "commodityName:" + commodityName;
    }

    public static String staticMethod(String orderId, String amount, String commodityName) {
        return "test static method success!orderId:" + orderId + ",amount:" + amount + "commodityName:" + commodityName;
    }
}
