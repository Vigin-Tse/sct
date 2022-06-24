package com.vg.sct.feign.product.hystrix;

import com.vg.sct.common.support.exception.UnCallableException;
import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.feign.product.api.ProSeataStorageFeignApi;
import org.springframework.stereotype.Component;

/**
 * @author: xieweij
 * @time: 2021/6/24 14:05
 */
@Component
public class ProSeataStorageFeignFallBack implements ProSeataStorageFeignApi {

    @Override
    public HttpResponse deduct(Integer productId, Integer count) {
        throw new UnCallableException("服务无法调用");
    }
}
