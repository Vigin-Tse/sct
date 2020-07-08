package com.vg.sct.order.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 10:20
 */
@FeignClient(name = "sct-sys")
public interface SysRemote {

    @RequestMapping("/sys/hello")
    String getRemoteHello();
}
