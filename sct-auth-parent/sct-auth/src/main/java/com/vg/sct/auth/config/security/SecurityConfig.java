package com.vg.sct.auth.config.security;

import com.vg.sct.common.utils.MD5Utils;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @description: SpringSecurity配置
 * @author: xieweij
 * @create: 2021-01-21 15:48
 *
 * @EnableWebSecurity
 * 1:加载了WebSecurityConfiguration配置类, 配置安全认证策略。
 * 2:加载了AuthenticationConfiguration, 配置了认证信息。
 *
 * @EnableGlobalMethodSecurity
 * 向Web应用程序添加方法级安全性
 *
 *  @EnableGlobalMethodSecurity(securedEnabled=true)
 *          开启@Secured 注解过滤权限
 *
 * @EnableGlobalMethodSecurity(jsr250Enabled=true)
 *
 *           开启@RolesAllowed 注解过滤权限
 *
 * @EnableGlobalMethodSecurity(prePostEnabled=true)
 *          使用表达式时间方法级别的安全性 4个注解可用
 *
 *     @PreAuthorize 在方法调用之前, 基于表达式的计算结果来限制对方法的访问
 *     @PostAuthorize 允许方法调用, 但是如果表达式计算结果为false, 将抛出一个安全性异常
 *     @PostFilter 允许方法调用, 但必须按照表达式来过滤方法的结果
 *     @PreFilter 允许方法调用, 但必须在进入方法之前过滤输入值
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 用于配置需要拦截的url路径、jwt过滤器及出异常后的处理器；
     * @param httpSecurity
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf().disable()  //由于使用的是JWT，我们这里不需要csrf
//                .cors(Customizer.withDefaults()) // by default uses a Bean by the name of corsConfigurationSource(官方说明，使下面配置的bean生效)
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)  //基于token，禁用session 默认授权页面将失效
//                .and()
                .authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()  //所有endpoint，permitAll() 无条件允许访问
                .anyRequest().authenticated() //除上面外的所有请求全部需要鉴权认证
                .and()
                .formLogin().permitAll();     //新增login form支持用户登录及授权

//        httpSecurity.csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/oauth/**", "/login/**", "/logout/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .permitAll();
    }

    /**
     * 跨域配置
     * @return
     */
//    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");//修改为添加而不是设置，* 最好改为实际的需要，我这是非生产配置，所以粗暴了一点
        configuration.addAllowedMethod("*");//修改为添加而不是设置
        configuration.addAllowedHeader("*");//这里很重要，起码需要允许 Access-Control-Allow-Origin
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * 密码模式下必须注入的bean authenticationManagerBean
     * 认证是由 AuthenticationManager 来管理的，
     * 但是真正进行认证的是 AuthenticationManager 中定义的AuthenticationProvider。
     *  AuthenticationManager 中可以定义有多个 AuthenticationProvider
     *
     * @return
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }


    /**
     * SpringSecurity定义的用于对密码进行编码及比对的接口，目前选用md5(也可选用其他例如：目前使用的是BCryptPasswordEncoder)
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return MD5Utils.encodeMd5(charSequence.toString());
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {

                return MD5Utils.comparison(charSequence.toString(), s);
            }
        };
    }

    /**
     *     RestfulAccessDeniedHandler：当用户没有访问权限时的处理器，用于返回JSON格式的处理结果；
     *     RestAuthenticationEntryPoint：当未登录或token失效时，返回JSON格式的结果；
     *     JwtAuthenticationTokenFilter：在用户名和密码校验前添加的过滤器，如果有jwt的token，会自行根据token信息进行登录。
     */
}
