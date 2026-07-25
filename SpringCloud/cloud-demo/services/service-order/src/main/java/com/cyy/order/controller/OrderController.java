package com.cyy.order.controller;

import com.cyy.order.bean.Order;
import com.cyy.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    //创建订单
    @GetMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId, @RequestParam("userId") Long userId) {
        return orderService.createOrder(productId, userId);
    }
}
