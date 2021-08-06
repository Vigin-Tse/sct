package com.vg.sct.sys.controller;

import com.vg.sct.common.domain.vo.UserPsLoginRquest;
import com.vg.sct.common.support.context.LoginUserContext;
import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.sys.domain.dto.UserUpdateDto;
import com.vg.sct.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description: 用户
 * @author: xieweij
 * @create: 2020-12-30 16:50
 **/
@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @PostMapping("/login")
    public HttpResponse userLogin(@RequestBody UserPsLoginRquest request) {
        LoginUserContext.getCurrentUser();
        return this.userService.loginByUserNameAndPsw(request.getUserName(), request.getPassword());
    }

    @GetMapping("/userinfo")
    public HttpResponse userInfo(@RequestParam Integer userId) {
        return HttpResponseConvert.success(this.userService.getUserInfo(userId));
    }

    @PostMapping("/add")
    public HttpResponse createUser(@RequestBody UserUpdateDto dto){
        return HttpResponseConvert.success(this.userService.createUser(dto));
    }

    @PostMapping("/update")
    public HttpResponse updateUser(@RequestBody UserUpdateDto dto){
        return HttpResponseConvert.success(this.userService.updateUser(dto));
    }
}
