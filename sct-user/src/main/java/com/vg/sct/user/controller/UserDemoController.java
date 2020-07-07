package com.vg.sct.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/7 16:30
 */
@RestController
@RequestMapping("/user/demo")
public class UserDemoController {

    @GetMapping("/hello")
    public String hello(){
        return "hello user 8081";
    }
}
