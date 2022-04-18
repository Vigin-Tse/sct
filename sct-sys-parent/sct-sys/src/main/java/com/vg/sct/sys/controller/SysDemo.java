package com.vg.sct.sys.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.feign.auth.api.OauthFeignApi;
import com.vg.sct.feign.product.api.ProductFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author: xieweij
 * @time: 2021/6/8 16:11
 */
@RestController
@RequestMapping("/demo")
@Slf4j
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
        long start = System.currentTimeMillis();
        HttpResponse resp = null;
        try {
            resp = productFeignApi.demo();
        }catch (Exception e){

        }
        log.info("/demo/plb接口耗时：{}s", (System.currentTimeMillis() - start) / 1000);
        return resp;
    }
}
