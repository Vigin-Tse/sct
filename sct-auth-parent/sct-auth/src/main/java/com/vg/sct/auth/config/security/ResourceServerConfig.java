package com.vg.sct.auth.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @description: 资源服务器配置
 * @author: xieweij
 * @create: 2021-02-02 10:52
 **/
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                   .anyRequest().authenticated()
                .and()
                .requestMatchers()  //配置需要（token）保护的资源
//                   .anyRequest()  //拦截所有请求（需要携带登录凭证（token））
                   .antMatchers("/rs/**")
//                   .antMatchers("/rs2/**")
        ;
    }
}
