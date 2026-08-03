package com.cyy.order.service.impl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;


import com.cyy.order.bean.Order;
import com.cyy.order.feign.ProductFeignClient;
import com.cyy.order.service.OrderService;
import com.cyy.product.bean.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    LoadBalancerClient loadBalancerClient;
//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    ProductFeignClient productFeignClient;
    @Override
    public Order createOrder(Long productId, Long user) {
        Product product = productFeignClient.getProductById(productId);
        Order order = new Order();
        order.setId(0L);
        order.setTotalAmount(product.getPrice().multiply(new BigDecimal(product.getNum())));
        order.setUserId(user);
        order.setNickName("cyy");
        order.setAddress("翻斗花园");
        order.setProductList(Arrays.asList(product));



        return order;
    }
    private Product getProductFromRemote(Long productId) {
        //第一版 discoveryClient
//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        String url =instance.getUri()+"/product/"+productId;

        //第二版 负载均衡
//        ServiceInstance instance = loadBalancerClient.choose("service-product");
//        String url =instance.getUri()+"/product/"+productId;

        //第三版 注解
        String url = "http://service-product/product/"+productId;
        log.info("远程请求路径url:{}",url);
        Product product = productFeignClient.getProductById(productId);
        return product;
    }
}
