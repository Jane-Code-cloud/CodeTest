package com.cyy.product.service.impl;

import com.cyy.product.bean.Product;
import com.cyy.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(productId);
        product.setName("手机");
        product.setPrice(new BigDecimal("10.00"));
        product.setNum(100);
        return product;
    }
}
