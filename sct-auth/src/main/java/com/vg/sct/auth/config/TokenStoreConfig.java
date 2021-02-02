package com.vg.sct.auth.config;

import com.vg.sct.common.utils.JwtUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @description: 存储token配置
 * @author: xieweij
 * @create: 2021-02-02 14:05
 **/
@Configuration
public class TokenStoreConfig {

    /**
     * 配置使用jwt存储token
     * @return
     */
    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey(JwtUtils.getSecretKey()); //配置jwt秘钥
        return accessTokenConverter;
    }

    /**
     * 实例化 jwt增强器
     * @return
     */
    @Bean
    public JwtTokenEnhancer jwtTokenEnhancer(){
        return new JwtTokenEnhancer();
    }
}
