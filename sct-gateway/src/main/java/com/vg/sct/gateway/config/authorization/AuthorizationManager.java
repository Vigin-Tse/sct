package com.vg.sct.gateway.config.authorization;

import com.vg.sct.common.constants.AuthConstants;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Mono;

/**
 * @description: 鉴权管理器 是作为资源服务器验证是否有权访问资源的裁决者
 * @author: xieweij
 * @create: 2021-03-05 15:50
 **/
@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();
        PathMatcher pathMatcher = new AntPathMatcher();

        // 1. 跨域的预检请求直接放行
        if(request.getMethod() == HttpMethod.OPTIONS){
            return Mono.just(new AuthorizationDecision(true));
        }

        // 2. token为空拒绝访问
        String token = request.getHeaders().getFirst(AuthConstants.JWT_TOKEN_HEADER);
        if (StringUtils.isEmpty(token)){
            return Mono.just(new AuthorizationDecision(false));
        }

        //todo...授权(验证角色权限)

        return Mono.just(new AuthorizationDecision(true));
    }
}
