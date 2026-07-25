package com.cyy.product.controller;

import com.cyy.product.bean.Product;
import com.cyy.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    //查询商品
    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable("id") Long productId){
        Product product = productService.getProductById(productId);
        return product;
    }
}
