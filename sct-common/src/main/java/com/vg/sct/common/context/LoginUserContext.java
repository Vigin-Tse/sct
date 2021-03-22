package com.vg.sct.common.context;

import com.vg.sct.common.constants.AuthConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 获取登录用户信息
 * @author: xieweij
 * @create: 2021-03-22 16:58
 **/
public class LoginUserContext{

    public String getCurrentUser(){
        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String userStr = request.getHeader(AuthConstants.JWT_TOKEN_HEADER);
        System.out.println("token传递：" + userStr);
        return userStr;
    }

}
