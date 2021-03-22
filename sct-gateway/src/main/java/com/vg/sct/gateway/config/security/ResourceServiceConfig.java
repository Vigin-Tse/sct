package com.vg.sct.gateway.config.security;

import cn.hutool.core.util.ArrayUtil;
import com.vg.sct.gateway.config.authorization.AuthorizationManager;
import com.vg.sct.gateway.config.component.CustomServerAccessDeniedHandler;
import com.vg.sct.gateway.config.component.CustomServerAuthenticationEntryPoint;
import com.vg.sct.gateway.config.component.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

/**
 * @description: 资源服务器配置
 * @author: xieweij
 * @create: 2021-02-05 16:55
 **/
@Configuration
@EnableWebFlux //Gateway使用的是WebFlux，所以需要使用@EnableWebFluxSecurity注解开启。注意@EnableWebSecurity无效！
public class ResourceServiceConfig {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig; //注入白名单配置列表

    //无权访问自定义响应
    @Autowired
    private CustomServerAccessDeniedHandler serverAccessDeniedHandler;

    //自定义处理JWT请求头过期或签名错误的结果
    @Autowired
    private CustomServerAuthenticationEntryPoint serverAuthenticationEntryPoint;

    @Autowired
    private AuthorizationManager authorizationManager;


    @Bean
    public SecurityWebFilterChain srpingSecurityWebFilterChain(ServerHttpSecurity http) {

        http.oauth2ResourceServer().jwt()
                .jwtAuthenticationConverter(jwtAuthenticationConverter());

        //自定义处理JWT请求头过期或签名错误的结果
        http.oauth2ResourceServer().authenticationEntryPoint(serverAuthenticationEntryPoint);

        http
            .authorizeExchange()
                .pathMatchers(ArrayUtil.toArray(ignoreUrlsConfig.getUrls(),String.class)).permitAll()//白名单配置
                .anyExchange().access(authorizationManager)//鉴权管理器配置
                .and().exceptionHandling()
                .accessDeniedHandler(serverAccessDeniedHandler)//处理未授权
                .authenticationEntryPoint(serverAuthenticationEntryPoint)//处理未认证
                .and()
                .csrf().disable();

        return http.build();
    }

    /**
     * @link https://blog.csdn.net/qq_24230139/article/details/105091273
     * ServerHttpSecurity没有将jwt中authorities的负载部分当做Authentication
     * 需要把jwt的Claim中的authorities加入
     * 方案：重新定义ReactiveAuthenticationManager权限管理器，默认转换器JwtGrantedAuthoritiesConverter
     */
    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
//        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }
}