package com.vg.sct.user.service.impl;

import com.netflix.discovery.converters.Auto;
import com.vg.sct.common.exception.AuthException;
import com.vg.sct.common.exception.BusinessException;
import com.vg.sct.common.utils.MD5Utils;
import com.vg.sct.user.domain.model.SysUserModel;
import com.vg.sct.user.domain.vo.UserLoginInfoVo;
import com.vg.sct.user.repository.SysUserRepository;
import com.vg.sct.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:49
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository userRepository;

    @Override
    public UserLoginInfoVo loginByUserNameAndPsw(String userName, String pwd) {
        List<SysUserModel> userList = this.userRepository.findByUserNameAndIsActiveTrue(userName);
        if (CollectionUtils.isEmpty(userList)){
            throw new AuthException("用户名不存在，或密码不正确");
        }

        SysUserModel user = userList.get(0);
        String oldPwd = user.getPassword();

        if(!MD5Utils.comparison(pwd, oldPwd)){
            throw new AuthException("用户名不存在，或密码不正确");
        }

        //todo 密码校验通过，生成用户token
        String accessToken = "token";

        //todo 考虑是否应该把token-用户信息放到redis

        UserLoginInfoVo result = new UserLoginInfoVo();
        result.setUserName(user.getUserName());
        result.setAccessToken(accessToken);

        return result;
    }
}
