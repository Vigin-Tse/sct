package com.vg.sct.gateway.config.authorization;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nimbusds.jose.JWSObject;
import com.vg.sct.common.constants.AuthConstants;
import com.vg.sct.common.constants.redis.RedisNamespaceConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> mono, AuthorizationContext authorizationContext) {
        log.info("进入鉴权管理器");

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

        //3.从redis中取出用户token，校验token是否（注销）失效
        String userId = "";
        String realToken = "";
        try {
            realToken = token.replace(AuthConstants.JWT_TOKEN_PREFIX, "");
            JWSObject jwsObject = JWSObject.parse(realToken);
            JSONObject userObject = JSON.parseObject(jwsObject.getPayload().toString());

            userId = Convert.toInt(userObject.get("user_id")).toString();
        }catch (Exception e){
            e.printStackTrace();
        }

        String redisToken = (String) this.redisTemplate.opsForValue().get(RedisNamespaceConstants.USER_LOGIN_TOKEN_NAMESPACE + userId);
        if (StringUtils.isEmpty(redisToken) || !redisToken.equals(realToken)){
            return Mono.just(new AuthorizationDecision(false));
        }


        //todo...授权(验证角色权限)

        return Mono.just(new AuthorizationDecision(true));
    }
}
