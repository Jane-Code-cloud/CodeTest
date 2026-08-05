package com.cyy.product;

import com.alibaba.cloud.nacos.discovery.NacosDiscoveryClient;
import net.bytebuddy.utility.nullability.AlwaysNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.List;

@SpringBootTest
public class DiscoveryTest {
    @Autowired
    DiscoveryClient discoveryClient;
    @Autowired
    NacosDiscoveryClient nacosDiscoveryClient;
    @Test
    void discoveryClientTest(){
        for(String service: discoveryClient.getServices()){
            System.out.println(service);
            List<ServiceInstance> serviceInstances = discoveryClient.getInstances(service);
            for(ServiceInstance serviceInstance: serviceInstances){
                System.out.println(serviceInstance.getHost() + ":" + serviceInstance.getPort());
            }
        }
    }
}
