package com.vg.sct.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 9:56
 */
@RestController
public class ProductDemoController {

    @GetMapping("/hello")
    public String hello(){
        return "hello product 8002";
    }
}
