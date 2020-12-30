package com.vg.sct.user.controller;

import com.vg.sct.common.domain.vo.UserPsLoginRquest;
import com.vg.sct.common.http.HttpResponse;
import com.vg.sct.common.http.HttpResponseConvert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 用户权限认证
 * @author: xieweij
 * @create: 2020-12-30 16:50
 **/
@RestController
@RequestMapping("/auth")
public class AuthorityController {

    @RequestMapping("/user/login")
    @PostMapping
    public HttpResponse userlogin(@RequestBody UserPsLoginRquest request){
        return HttpResponseConvert.success(null);
    }
}
