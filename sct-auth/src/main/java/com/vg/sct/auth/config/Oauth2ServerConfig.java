package com.vg.sct.auth.config;

import com.vg.sct.auth.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 认证服务器配置
 * @author: xieweij
 * @create: 2021-01-28 10:11
 **/
@Configuration
@EnableAuthorizationServer //EnableAuthorizationServer注解让这个项目成为了认证服务器
public class Oauth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private TokenStore jwtTokenStore;

    @Resource
    private JwtAccessTokenConverter JwtAccessTokenConverter;

    @Resource
    private JwtTokenEnhancer jwtTokenEnhancer;

    /**
     * 这个configurer定义了客户端细节服务。客户详细信息可以被初始化。
     * @param client
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
        client.inMemory()
                .withClient("admin") //配置client_id
                .secret(passwordEncoder.encode("client_secret")) //配置client_secret
                .accessTokenValiditySeconds(3600) //配置访问token的有效期
                .refreshTokenValiditySeconds(864000) //配置刷新token的有效期
                .redirectUris("http://www.baidu.com")//配置redirect_uri，用于授权成功后跳转
                .scopes("all")//配置申请的权限范围
                .authorizedGrantTypes("authorization_code","password"); //配置grant_type，表示授权类型
    }

    /**
     * 定义了授权和令牌端点和令牌服务
     * 使用密码模式需要配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {

        TokenEnhancerChain chain = new TokenEnhancerChain();

        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(this.jwtTokenEnhancer);
        delegates.add(this.JwtAccessTokenConverter);

        chain.setTokenEnhancers(delegates);

        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userService)
                .tokenStore(jwtTokenStore)
                .accessTokenConverter(JwtAccessTokenConverter)
                .tokenEnhancer(chain);
    }

    /**
     * 在令牌端点上定义了安全约束
     * @param serviceSecurity
     */
/*    @Override
    public void configure(AuthorizationServerSecurityConfigurer serviceSecurity){
        //开启支持通过表单方式提交client_id和client_secret,否则请求时以basic auth方式,头信息传递Authorization发送请求
        serviceSecurity.allowFormAuthenticationForClients();
    }*/
}