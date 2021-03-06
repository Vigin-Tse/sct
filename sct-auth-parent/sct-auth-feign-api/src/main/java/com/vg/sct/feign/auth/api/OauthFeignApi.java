package com.vg.sct.feign.auth.api;

import com.vg.sct.common.support.http.HttpResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * 调用远程服务
 */
@FeignClient(contextId = "oauthFeignApi", name = "sct-auth", path = "/sct-auth/oauth")
public interface OauthFeignApi {

    /**
     * 登录获取token
     * @param param
     * @return
     */
    @PostMapping("/token")
    HttpResponse postAccessToken(@RequestParam Map<String, String> param);

    @GetMapping("/demo")
    HttpResponse demo();
}
