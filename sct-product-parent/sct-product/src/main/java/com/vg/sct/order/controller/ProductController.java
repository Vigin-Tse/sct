package com.vg.sct.order.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xieweij
 * @time: 2021/6/9 15:41
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/demo")
    public HttpResponse demo() throws InterruptedException {
        log.info("远程调用接口：demo-{},进入等待", port);
        Thread.sleep(4500);
        log.info("远程调用接口：demo-{}，等待结束", port);
        int i = 1/0;
        return HttpResponseConvert.success("远程调用接口：demo-" + port);
    }
}
