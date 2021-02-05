package com.vg.sct.auth.controller;

import com.vg.sct.common.http.HttpResponse;
import com.vg.sct.common.http.HttpResponseConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: (重写)自定义oauth2令牌相关接口
 * @author: xieweij
 * @create: 2021-02-03 16:15
 **/
@RestController
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    /**
     * 统一登录获取token
     * @param principal
     * @param param
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @PostMapping("/token")
    public HttpResponse postAccessToken(Principal principal, @RequestParam  Map<String, String> param) throws HttpRequestMethodNotSupportedException {
        OAuth2AccessToken auth2AccessToken = this.tokenEndpoint.postAccessToken(principal, param).getBody();

        //提取登录成功后用户信息集合
        Map<String, Object> additionalInfo = auth2AccessToken.getAdditionalInformation();

        //设置登录成功返回用户信息
        Map<String, Object> userLoginSuccesInfo = new HashMap<>();
        userLoginSuccesInfo.put("userId", additionalInfo.get("user_id"));
        userLoginSuccesInfo.put("userName", additionalInfo.get("user_name"));
        userLoginSuccesInfo.put("nickName", additionalInfo.get("nick_name"));
        userLoginSuccesInfo.put("token", "bearer " + auth2AccessToken.getValue());

        return new HttpResponseConvert().success("登录成功", userLoginSuccesInfo);
    }
}
