package com.vg.sct.feign.product.api;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.feign.product.hystrix.ProSeataStorageFeignFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: xieweij
 * @time: 2021/7/14 15:44
 */
@FeignClient(contextId = "proSeataStorageFeignApi", name = "sct-product", path = "/sct-product/seata", fallback = ProSeataStorageFeignFallBack.class)
public interface ProSeataStorageFeignApi {

    @GetMapping("/deduct")
    HttpResponse deduct(@RequestParam Integer productId, @RequestParam Integer count);
}
