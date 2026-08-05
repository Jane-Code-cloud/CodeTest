package com.cyy.order;

import com.alibaba.cloud.nacos.NacosConfigManager;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import javax.swing.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@EnableFeignClients
@SpringBootApplication
public class OrderMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainApplication.class, args);
    }

    //1. 项目启动就监听配置文件变化
    //2. 发生变化后拿到变化值
    //3. 发送邮件

    @Bean//方法上的组件会自动从容器中拿
    ApplicationRunner applicationRunner(NacosConfigManager nacosConfigManager){//一次性任务，项目只要启动起来就会执行这个任务
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                ConfigService configService = nacosConfigManager.getConfigService();
                configService.addListener("service-order.properties", "DEFAULT_GROUP", new Listener(){
                    @Override
                    public void receiveConfigInfo(String configInfo) {
                        System.out.println("配置文件变化了："+configInfo);
                        System.out.println("发送邮件");
                    }
                    @Override
                    public Executor getExecutor() {
                        return Executors.newFixedThreadPool(4);
                    }
                });
                System.out.println("========");
            }
        };
    }
}
