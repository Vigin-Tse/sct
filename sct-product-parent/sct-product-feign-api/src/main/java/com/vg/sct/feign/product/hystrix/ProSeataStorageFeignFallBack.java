package com.vg.sct.feign.product.hystrix;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import com.vg.sct.feign.product.api.ProSeataStorageFeignApi;
import com.vg.sct.feign.product.api.ProductFeignApi;
import org.springframework.stereotype.Component;

/**
 * @author: xieweij
 * @time: 2021/6/24 14:05
 */
@Component
public class ProSeataStorageFeignFallBack implements ProSeataStorageFeignApi {

    @Override
    public HttpResponse deduct(Integer productId, Integer count) {
        return HttpResponseConvert.failure("服务调用失败" );
    }
}
