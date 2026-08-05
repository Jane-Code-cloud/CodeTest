package com.cyy.order.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties(prefix = "order")//可以实现自动刷新
public class OrderProperties {
    String timeout;
    String autoConfirm;
    String dbUrl;
}
