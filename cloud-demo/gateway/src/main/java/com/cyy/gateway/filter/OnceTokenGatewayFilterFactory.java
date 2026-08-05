package com.cyy.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class OnceTokenGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {
    @Override
    public GatewayFilter apply(NameValueConfig config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                //每次请求前添加一个一次性令牌 uuid

                return chain.filter(exchange).then(Mono.fromRunnable(()->{
                    String value = config.getValue();
                    if("uuid".equals(value)){
                        value = UUID.randomUUID().toString();
                    }
                    else if("jwt".equals( value)){
                        value = "这是一个jwt...";
                    }
                    exchange.getResponse().getHeaders().add(config.getName(),value);
                }));
            }
        };
    }
}
