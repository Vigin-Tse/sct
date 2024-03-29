package com.vg.sct.feign.account.api;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.feign.account.hystrix.AccSeataAccountFeignFallBack;
import com.vg.sct.feign.account.hystrix.AccSeataAccountFeignFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 调用远程服务
 */
@FeignClient(contextId = "accSeataAccountFeignApi", name = "sct-account", path = "/sct-account/seata", fallbackFactory = AccSeataAccountFeignFallBackFactory.class)
//        , fallback = AccSeataAccountFeignFallBack.class)
public interface AccSeataAccountFeignApi {

    @GetMapping("/debit")
    public HttpResponse debit(@RequestParam Integer userId, @RequestParam Double payMoney);

    @GetMapping("/test")
    HttpResponse test();
}
