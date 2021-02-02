package com.vg.sct.auth.controller;

import com.vg.sct.common.http.HttpResponse;
import com.vg.sct.common.http.HttpResponseConvert;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户测试
 * @author: xieweij
 * @create: 2021-02-02 09:01
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/getCurrentUser")
    public HttpResponse getCurrentUser(Authentication authentication) {
        return HttpResponseConvert.success(authentication.getPrincipal());
    }
}
