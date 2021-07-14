package com.vg.sct.order.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.order.service.BizSeataOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xieweij
 * @time: 2021/7/14 15:53
 */
@RestController
@RequestMapping("/seata")
public class BizSeataOrderController {

    @Autowired
    private BizSeataOrderService orderService;

    @GetMapping("/notranscation/order")
    public HttpResponse notranscationOrder(){
        this.orderService.createOrderWithNoTransaction();
        return HttpResponseConvert.success(null);
    }
}
