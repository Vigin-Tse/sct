package com.vg.sct.user.controller;

import com.vg.sct.user.dao.model.SysUserModel;
import com.vg.sct.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/user/get/{id}")
    public SysUserModel getOneUser(@PathVariable("id") Integer id){
        return this.userService.getOne(id);
    }
}
