package com.vg.sct.auth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;

/**
 * @description: 存储token配置
 * @author: xieweij
 * @create: 2021-02-02 14:05
 **/
@Configuration
public class TokenStoreConfig {

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;

    /**
     * 配置jwt存储token策略
     * @return
     */
    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 配置redis存储token策略
     * @return
     */
//    @Bean
//    public TokenStore redisTokenStore(){
//        return new RedisTokenStore(this.redisConnectionFactory);
//    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
//        accessTokenConverter.setSigningKey(JwtUtils.getSecretKey()); //配置jwt秘钥(对称加密)
        accessTokenConverter.setKeyPair(keyPair());//非对称加密
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

    /**
     * 配置从证书中获取密钥对
     * @return
     */
    @Bean
    public KeyPair keyPair(){
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("jwt/jwt.jks"), "scarf123".toCharArray());
        return keyStoreKeyFactory.getKeyPair("jwt", "scarf123".toCharArray());
    }
}
