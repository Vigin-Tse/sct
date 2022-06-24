package com.vg.sct.feign.account.hystrix;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.feign.account.api.AccSeataAccountFeignApi;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: xieweij
 * @time: 2021/6/24 14:05
 */
//@Component
public class AccSeataAccountFeignFallBack implements AccSeataAccountFeignApi {


    @Override
    public HttpResponse debit(Integer userId, Double payMoney) {
        return HttpResponseConvert.failure("服务调用失败" );
    }

    @Override
    public HttpResponse test() {
        return null;
    }
}
