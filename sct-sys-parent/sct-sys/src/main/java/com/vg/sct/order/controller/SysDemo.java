package com.vg.sct.order.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.feign.auth.api.OauthFeignApi;
import com.vg.sct.feign.product.api.ProductFeignApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: xieweij
 * @time: 2021/6/8 16:11
 */
@RestController
@RequestMapping("/demo")
public class SysDemo {

    @Resource
    private OauthFeignApi oauthFeignApi;

    @Resource
    private ProductFeignApi productFeignApi;

    @GetMapping("/lb")
    public HttpResponse demo(){
//        return oauthFeignApi.demo();
        return HttpResponseConvert.success("成功");
    }

    @GetMapping("/plb")
    public HttpResponse pDemo(){
        return productFeignApi.demo();
    }
}
