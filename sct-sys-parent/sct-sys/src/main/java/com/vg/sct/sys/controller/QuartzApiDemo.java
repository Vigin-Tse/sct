package com.vg.sct.sys.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.sys.domain.dto.QuartzApiRequest;
import org.springframework.web.bind.annotation.*;

/**
 * 提供给调度中心调用接口的测试
 * @author: xieweij
 * @time: 2021/8/6 16:10
 */
@RestController
@RequestMapping("/job")
public class QuartzApiDemo {

    @GetMapping("/get")
    public HttpResponse get(QuartzApiRequest request){
        String str = request.getName() + "，" + request.getAge();
        System.out.println(str);
        return HttpResponseConvert.success(str);
    }

    @PostMapping("/post")
    public HttpResponse post(@RequestBody  QuartzApiRequest request){
        String str = request.getName() + "，" + request.getAge();
        System.out.println(str);
        return HttpResponseConvert.success(str);
    }
}
