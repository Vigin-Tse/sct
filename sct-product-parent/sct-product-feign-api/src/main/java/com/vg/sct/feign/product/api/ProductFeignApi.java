package com.vg.sct.feign.product.api;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.feign.product.hystrix.ProductFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 调用远程服务
 */
@FeignClient(contextId = "productFeignApi", name = "sct-product", path = "/sct-product/product", fallback = ProductFeignFallBack.class)
public interface ProductFeignApi {

    @GetMapping("/demo")
    HttpResponse demo();
}
