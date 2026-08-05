package com.cyy.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.cyy.order.mapper")
@EnableDiscoveryClient
@SpringBootApplication
@EnableTransactionManagement
@EnableFeignClients(basePackages = "com.cyy.order.feign")
public class SeataOrderMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderMainApplication.class, args);
    }


}
