package com.vg.sct.order.controller;

import com.vg.sct.order.feignclient.SysRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 9:57
 */
@RestController
public class OrderDemoController {

    @Autowired
    private SysRemote sysRemote;

    @GetMapping("/demo/sayhello")
    public String hello(){
        return sysRemote.getRemoteHello();
    }

}
