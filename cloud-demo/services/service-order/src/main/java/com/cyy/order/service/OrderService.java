package com.cyy.order.service;

import com.cyy.order.bean.Order;

public interface OrderService {
    Order createOrder(Long productId,Long user);
}
