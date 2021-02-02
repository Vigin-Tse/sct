package com.vg.sct.auth.config;

import com.vg.sct.auth.domain.security.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Jwt内容增强器（扩展jwt内容）
 * @author: xieweij
 * @create: 2021-02-02 14:35
 **/
public class JwtTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication auth) {

        SecurityUser user = (SecurityUser) auth.getPrincipal();

        Map<String, Object> info = new HashMap<>();
        info.put("user_id", user.getId());
        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
