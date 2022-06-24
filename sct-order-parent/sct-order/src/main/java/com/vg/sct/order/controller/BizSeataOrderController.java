package com.vg.sct.order.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.feign.account.api.AccSeataAccountFeignApi;
import com.vg.sct.order.service.BizSeataOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: xieweij
 * @time: 2021/7/14 15:53
 */
@RestController
@RequestMapping("/seata")
public class BizSeataOrderController {

    @Autowired
    private BizSeataOrderService orderService;

    @Resource
    private AccSeataAccountFeignApi accSeataAccountFeignApi;

    /**
     * 分布式不加事务测试
     * @return
     */
    @GetMapping("/notranscation/order")
    public HttpResponse notranscationOrder(){
        this.orderService.createOrderWithNoTransaction();
        return HttpResponseConvert.success(null);
    }

    /**
     * 分布式事务seata测试
     * @return
     */
    @GetMapping("/seatatransacation/order")
    public HttpResponse seatatransacation(){
        this.orderService.createOrderWithSeata();
        return HttpResponseConvert.success(null);
    }

    /**
     * 本地事务Transaction测试
     * @return
     */
    @GetMapping("/localtransacation/order")
    public HttpResponse localtransacation(){
        this.orderService.localTransaction();
        return HttpResponseConvert.success(null);
    }

    @GetMapping("/accTesting")
    public HttpResponse accTesting(){
        return this.accSeataAccountFeignApi.test();
    }
}
