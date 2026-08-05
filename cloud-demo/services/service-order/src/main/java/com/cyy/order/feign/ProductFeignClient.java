package com.cyy.order.feign;

import com.cyy.order.feign.fall.back.ProductFeignClientFallback;
import com.cyy.product.bean.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


@FeignClient(value="service-product",fallback = ProductFeignClientFallback.class)
public interface ProductFeignClient {
    @GetMapping("/product/{id}")//在springMVC上是接收这样的请求，在openFeign上是发送这样的请求
    Product getProductById(@PathVariable("id")Long id);//id在springMVC上是接收的参数，在openFeign上是发送的参数
}
