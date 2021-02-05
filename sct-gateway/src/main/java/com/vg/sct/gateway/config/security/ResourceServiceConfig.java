package com.vg.sct.gateway.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @description: 资源服务器配置
 * @author: xieweij
 * @create: 2021-02-05 16:55
 **/
@Configuration
@EnableWebFlux //Gateway使用的是WebFlux，所以需要使用@EnableWebFluxSecurity注解开启。注意@EnableWebSecurity无效！
public class ResourceServiceConfig {
}
