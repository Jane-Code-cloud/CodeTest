package com.cyy.order.feign.fall.back;

import com.cyy.order.feign.ProductFeignClient;
import com.cyy.product.bean.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductFeignClientFallback implements ProductFeignClient {
    @Override
    public Product getProductById(Long id) {
        System.out.println("fallback");
        Product product = new Product();
        product.setId(0L);
        product.setName("位置商品");
        product.setPrice(new BigDecimal("0.00"));
        product.setNum(0);
        return product;
    }
}
