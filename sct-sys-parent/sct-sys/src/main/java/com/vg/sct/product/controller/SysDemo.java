package com.vg.sct.product.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.feign.auth.api.OauthFeignApi;
import com.vg.sct.feign.product.ProductFeignApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xieweij
 * @time: 2021/6/8 16:11
 */
@RestController
@RequestMapping("/demo")
public class SysDemo {

    @Autowired
    private OauthFeignApi oauthFeignApi;

    @Autowired
    private ProductFeignApi productFeignApi;

    @GetMapping("/lb")
    public HttpResponse demo(){
        return oauthFeignApi.demo();
    }

    @GetMapping("/plb")
    public HttpResponse pDemo(){
        return productFeignApi.demo();
    }
}
