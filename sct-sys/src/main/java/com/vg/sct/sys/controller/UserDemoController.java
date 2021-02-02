package com.vg.sct.sys.controller;

import com.vg.sct.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/7 16:30
 */
@RestController
public class UserDemoController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "hello user 8001";
    }

}