package com.vg.sct.sys.controller;

import com.vg.sct.common.domain.vo.UserPsLoginRquest;
import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.sys.config.component.CurrentUserHolder;
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

    @Autowired
    private CurrentUserHolder currentUserHolder;

    @PostMapping("/login")
    public HttpResponse userLogin(@RequestBody UserPsLoginRquest request) {
        currentUserHolder.getCurrentUser();
        return this.userService.loginByUserNameAndPsw(request.getUserName(), request.getPassword());
    }

    @PostMapping("/userinfo")
    public HttpResponse userInfo(@RequestParam Integer userId) {
        currentUserHolder.getCurrentUser();
        return HttpResponseConvert.success(this.userService.getUserInfo(userId));
    }
}
