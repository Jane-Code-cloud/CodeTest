package com.cyy.order.service.impl;
import java.math.BigDecimal;
import java.util.List;


import com.cyy.order.bean.Order;
import com.cyy.order.service.OrderService;
import com.cyy.product.bean.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public Order createOrder(Long productId, Long user) {
        Order order = new Order();
        order.setId(0L);
        //TODO 总金额
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId(user);
        order.setNickName("cyy");
        order.setAddress("翻斗花园");
        //TODO 商品列表
        order.setProductList(null);



        return order;
    }
    private Product getProductFromRemote(Long productId) {
        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
        ServiceInstance instance = instances.get(0);
        String url ="http://"+ instance.getHost() + ":" + instance.getPort()+"/product/"+productId;
        Product product = restTemplate.getForObject(url, Product.class);
        return product;
    }
}
