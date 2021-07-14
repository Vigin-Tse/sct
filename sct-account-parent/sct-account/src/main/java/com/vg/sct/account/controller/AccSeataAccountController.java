package com.vg.sct.account.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.product.service.AccSeataAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xieweij
 * @time: 2021/7/14 14:43
 */
@RestController
@RequestMapping("/seata")
public class AccSeataAccountController {

    @Autowired
    private AccSeataAccountService accSeataAccountService;

    @GetMapping("/debit")
    public HttpResponse debit(@RequestParam Integer userId, @RequestParam Double payMoney){
        this.accSeataAccountService.debit(userId, payMoney);
        return HttpResponseConvert.success(null);
    }
}
