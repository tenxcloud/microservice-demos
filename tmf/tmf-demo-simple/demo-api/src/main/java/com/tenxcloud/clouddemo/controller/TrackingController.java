/*
 * Licensed Materials - Property of tenxcloud.com
 * (C) Copyright 2021 TenxCloud. All Rights Reserved.
 *
 */
package com.tenxcloud.clouddemo.controller;

import com.tenxcloud.clouddemo.entity.OrderInfo;
import com.tenxcloud.clouddemo.service.OrderPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: wudongliang
 * @Date: 2021/2/25 11:06
 */
@RestController
public class TrackingController {

    @Autowired
    private OrderPaymentService orderPayment;

    @GetMapping("/payment")
    public String createOrder(@RequestParam(value = "orderId", defaultValue = "01000000000000011111") String orderId,
                              @RequestParam(value = "commodityName", defaultValue = "OPPO V9") String commodityName,
                              @RequestParam(value = "amount" , defaultValue = "888") String amount) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        orderInfo.setCommodityName(commodityName);
        orderInfo.setAmount(amount);
        return orderPayment.orderPayment(orderId, amount, commodityName);
    }

    @GetMapping("/static/payment")
    public String staticMethod(@RequestParam(value = "orderId", defaultValue = "01000000000000011111") String orderId,
                              @RequestParam(value = "commodityName", defaultValue = "OPPO V9") String commodityName,
                              @RequestParam(value = "amount" , defaultValue = "888") String amount) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        orderInfo.setCommodityName(commodityName);
        orderInfo.setAmount(amount);
        return OrderPaymentService.staticMethod(orderId, amount, commodityName);
    }


}
