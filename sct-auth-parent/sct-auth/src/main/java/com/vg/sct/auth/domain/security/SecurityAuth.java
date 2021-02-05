package com.vg.sct.auth.domain.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @description: 请求登录用户权限
 * @author: xieweij
 * @create: 2021-01-28 13:45
 **/
public class SecurityAuth implements GrantedAuthority {

    Integer roleId;

    String roleCode;

    String roleName;

    @Override
    public String getAuthority() {
        return this.roleCode;
    }
}
