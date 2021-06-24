package com.vg.sct.common.support.config.intercceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: Fegin拦截器
 * @author: xieweij
 * @create: 2021-03-23 15:50
 **/
//@Configuration
public class FeginBaseInterceptor implements RequestInterceptor {


    @Override
    public void apply(RequestTemplate requestTemplate) {

        //当Feign开启Hystrix支持时，attributes==null。原因在于，Hystrix的默认隔离策略是THREAD（线程隔离），可修改隔离方案为SEMAPHORE（信号量隔离），不过该方案官方不推荐。
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null){
            HttpServletRequest request = attributes.getRequest();

            String token = request.getHeader("Authorization");
            if(!StringUtils.isEmpty(token)){
                //添加token
                requestTemplate.header("Authorization", request.getHeader("Authorization"));
            }
        }
    }
}
