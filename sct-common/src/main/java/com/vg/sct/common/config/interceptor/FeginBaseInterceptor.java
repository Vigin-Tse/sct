package com.vg.sct.common.config.interceptor;

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

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String token = request.getHeader("Authorization");
        if(!StringUtils.isEmpty(token)){
            //添加token
            requestTemplate.header("Authorization", request.getHeader("Authorization"));
        }
    }
}
