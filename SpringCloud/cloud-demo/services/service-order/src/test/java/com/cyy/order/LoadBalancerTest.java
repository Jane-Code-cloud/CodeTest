package com.cyy.order;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;


@SpringBootTest
public class LoadBalancerTest {
    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Test
    void test(){
        for (int i = 0; i < 10; i++) {
            System.out.println(loadBalancerClient.choose("service-product").getUri());
        }
    }
}
