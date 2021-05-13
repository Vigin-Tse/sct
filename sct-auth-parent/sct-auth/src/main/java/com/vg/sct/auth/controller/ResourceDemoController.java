package com.vg.sct.auth.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 资源Demo
 * @author: xieweij
 * @create: 2021-02-07 14:45
 **/
@RestController
public class ResourceDemoController {

    @GetMapping("/rs/demo")
    public HttpResponse getDemoStr(){
        return HttpResponseConvert.failure("success", "hello world");
    }


    @GetMapping("/rs2/demo")
    public HttpResponse getDemoStr2(){
        return HttpResponseConvert.failure("success", "hello world 2");
    }
}
