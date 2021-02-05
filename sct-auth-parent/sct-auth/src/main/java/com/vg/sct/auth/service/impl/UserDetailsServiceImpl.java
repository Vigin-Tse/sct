package com.vg.sct.auth.service.impl;

import com.vg.sct.auth.domain.security.SecurityUser;
import com.vg.sct.auth.repository.SysUserRepository;
import com.vg.sct.common.domain.po.sys.SysUserPo;
import com.vg.sct.common.exception.AuthException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @description: 用户管理业务类
 * @author: xieweij
 * @create: 2021-01-28 11:52
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserRepository userRepository;

    /**
     * 根据用户名从数据获取用户信息
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SecurityUser loadUserByUsername(String userName) throws UsernameNotFoundException {

        List<SysUserPo> users = this.userRepository.findByUserName(userName);
        if (CollectionUtils.isEmpty(users)){
            throw new AuthException("用户名或密码不正确！");
        }

        SecurityUser securityUser = new SecurityUser();
        BeanUtils.copyProperties(users.get(0), securityUser);

        //账号状态判断
        if(!securityUser.isEnabled()){
            throw new AuthException("账号不可用！");
        } else if(!securityUser.isAccountNonExpired()){
            throw new AuthException("账号已超过有效期！");
        } else if(!securityUser.isAccountNonLocked()){
            throw new AuthException("账号已锁定！");
        } else if(!securityUser.isCredentialsNonExpired()){
            throw new AuthException("凭证已过期！");
        }

        return securityUser;
    }


}
