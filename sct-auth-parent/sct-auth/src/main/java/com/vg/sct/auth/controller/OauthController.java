package com.vg.sct.auth.controller;

import com.vg.sct.auth.config.component.CurrentUserHolder;
import com.vg.sct.auth.config.security.Oauth2ServerConfig;
import com.vg.sct.common.support.constants.redis.RedisNamespaceConstants;
import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.common.support.http.HttpResponseConvert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private CurrentUserHolder currentUserHolder;

    @Autowired
    private RedisTemplate redisTemplate;

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

//        currentUserHolder.getCurrentUser();

        //提取登录成功后用户信息集合
        Map<String, Object> additionalInfo = auth2AccessToken.getAdditionalInformation();

        String token = auth2AccessToken.getValue();
        String userId = additionalInfo.get("user_id").toString();
        redisTemplate.opsForValue().set(RedisNamespaceConstants.USER_LOGIN_TOKEN_NAMESPACE + userId, token, Oauth2ServerConfig.ACCESS_TOKEN_VALIDITU_SECONDS, TimeUnit.SECONDS);

        //设置登录成功返回用户信息
        Map<String, Object> userLoginSuccesInfo = new HashMap<>();
        userLoginSuccesInfo.put("userId", userId);
        userLoginSuccesInfo.put("userName", additionalInfo.get("user_name"));
        userLoginSuccesInfo.put("nickName", additionalInfo.get("nick_name"));
        userLoginSuccesInfo.put("token", "Bearer " + token);


        return HttpResponseConvert.success("登录成功", userLoginSuccesInfo);
    }

    @GetMapping("/demo")
    public HttpResponse demo(){
        System.out.println("远程调用接口：demo-5001");
        return HttpResponseConvert.success("远程调用接口：demo-5001");
    }
}
