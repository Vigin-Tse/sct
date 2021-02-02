package com.vg.sct.sys.controller;

import com.vg.sct.common.domain.vo.UserPsLoginRquest;
import com.vg.sct.common.http.HttpResponse;
import com.vg.sct.common.http.HttpResponseConvert;
import com.vg.sct.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysUserService userService;

    @RequestMapping("/user/login")
    @PostMapping
    public HttpResponse userLogin(@RequestBody UserPsLoginRquest request){
        return HttpResponseConvert.success(this.userService.loginByUserNameAndPsw(request.getUserName(), request.getPassword()));
    }
}
