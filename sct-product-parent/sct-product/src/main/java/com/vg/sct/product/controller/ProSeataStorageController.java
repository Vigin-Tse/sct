package com.vg.sct.product.controller;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.product.service.ProSeataStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xieweij
 * @time: 2021/7/14 15:41
 */
@RestController
@RequestMapping("/seata")
public class ProSeataStorageController {

    @Autowired
    private ProSeataStorageService storageService;

    @GetMapping("/deduct")
    public HttpResponse deduct(@RequestParam Integer productId, @RequestParam Integer count){
        this.storageService.deduct(productId, count);
        return HttpResponseConvert.success(null);
    }
}
