package com.cyy.order.controller;

import com.cyy.order.bean.Order;
import com.cyy.order.properties.OrderProperties;
import com.cyy.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RefreshScope
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

//    @Value("${order.timeout}")
//    String orderTimeout;
//    @Value("${order.auto-confirm}")
//    String orderAutoConfirm;

    @Autowired
    OrderProperties orderProperties;

    @GetMapping("/config")
    public String getOrderTimeout() {
        return "orderTimeout: "+orderProperties.getTimeout()+
                " orderAutoConfirm: "+orderProperties.getAutoConfirm()+
                " dbUrl: "+orderProperties.getDbUrl();
    }

    //创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId, @RequestParam("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }
}
