package com.vg.sct.feign.account.hystrix;

import com.vg.sct.common.support.exception.UnCallableException;
import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.feign.account.api.AccSeataAccountFeignApi;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AccSeataAccountFeignFallBackFactory implements FallbackFactory<AccSeataAccountFeignApi> {
    @Override
    public AccSeataAccountFeignApi create(Throwable throwable) {
        log.error("异常原因:{}", throwable.getMessage(), throwable);

        return new AccSeataAccountFeignApi() {
            @Override
            public HttpResponse debit(Integer userId, Double payMoney) {
                throw new UnCallableException("服务无法调用，" + throwable.getMessage());
            }

            @Override
            public HttpResponse test() {
                throw new UnCallableException("服务无法调用，" + throwable.getMessage());
            }
        };
    }
}
