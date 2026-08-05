package com.cyy.gateway.predicate;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.cloud.gateway.handler.predicate.QueryRoutePredicateFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

@Component
public class VipRoutePredicateFactory extends AbstractRoutePredicateFactory<VipRoutePredicateFactory.Config> {

    public VipRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder(){
        return Arrays.asList("param","value");
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                ServerHttpRequest request = serverWebExchange.getRequest();
                String first = request.getQueryParams().getFirst(config.param);
                return StringUtils.hasText(first)&& first.equals("cyy");
            }
        };
    }


    /*
    可以配置的参数
     */
    @Validated
    public static class Config {
        private @NotEmpty String param;
        private @NotEmpty String value;

        public String getParam() {
            return this.param;
        }

        public VipRoutePredicateFactory.Config setParam(String param) {
            this.param = param;
            return this;
        }

        public String getValue() {
            return this.value;
        }

        public VipRoutePredicateFactory.Config setValue(String value) {
            this.value = value;
            return this;
        }
    }
}
