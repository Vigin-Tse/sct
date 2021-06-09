package com.vg.sct.feign.product;

import com.vg.sct.common.support.http.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 调用远程服务
 */
@FeignClient(name = "sct-product", path = "/sct-product/product")
public interface ProductFeignApi {


    @GetMapping("/demo")
    HttpResponse demo();
}
