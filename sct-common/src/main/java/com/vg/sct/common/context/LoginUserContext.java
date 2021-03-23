package com.vg.sct.common.context;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.vg.sct.common.constants.AuthConstants;
import com.vg.sct.common.domain.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 * @description: 获取登录用户信息
 * @author: xieweij
 * @create: 2021-03-22 16:58
 **/
public class LoginUserContext{

    private final Logger logger = LoggerFactory.getLogger(LoginUserContext.class);

    public CurrentUser getCurrentUser(){

        //从Header中获取用户信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader(AuthConstants.JWT_TOKEN_HEADER);

        logger.info("header token传递 =  {}", token);

        CurrentUser currentUser = null;
        if(!StringUtils.isEmpty(token)){

            try {
                currentUser = new CurrentUser();
                String realToken = token.replace(AuthConstants.JWT_TOKEN_PREFIX, "");
                JWSObject jwsObject = JWSObject.parse(realToken);
                String userStr  = jwsObject.getPayload().toString();
                logger.info("登录token解析 =  {}", jwsObject.getPayload().toString());

                JSONObject  userObject = JSON.parseObject(userStr);
                currentUser.setId(Convert.toInt(userObject.get("user_id")));
                currentUser.setWxId(userObject.getString("wx_id"));
                currentUser.setUserName(userObject.getString("user_name"));
                currentUser.setNickName(userObject.getString("nick_name"));
                currentUser.setEmail(userObject.getString("email"));
                currentUser.setPhoneNo(userObject.getString(userObject.getString("phone_no")));

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return currentUser;
    }

}
