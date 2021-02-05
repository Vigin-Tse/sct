package com.vg.sct.auth.domain.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @description: 请求登录的用户信息
 * @author: xieweij
 * @create: 2021-01-28 11:57
 **/
@Setter
@Getter
public class SecurityUser implements UserDetails {

    private Integer id;

    private String wxId;

    private String userName;

    private String password;

    private String nickName;

    private String phoneNo;

    private String email;

    private Boolean isActive;

    private List<SecurityAuth> auths;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    /**
     * 账号是否未过期
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否未锁定
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 账号凭证是否未过期
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账号是否可用
     * @return
     */
    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
