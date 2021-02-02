package com.vg.sct.sys.service.impl;

import com.vg.sct.common.domain.CurrentUserDetails;
import com.vg.sct.common.exception.AuthException;
import com.vg.sct.common.utils.JwtUtils;
import com.vg.sct.common.utils.MD5Utils;
import com.vg.sct.sys.domain.po.SysUserPo;
import com.vg.sct.sys.domain.vo.UserLoginInfoVo;
import com.vg.sct.sys.repository.SysUserRepository;
import com.vg.sct.sys.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:49
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository userRepository;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public UserLoginInfoVo loginByUserNameAndPsw(String userName, String pwd) {
        List<SysUserPo> userList = this.userRepository.findByUserNameAndIsActiveTrue(userName);
        if (CollectionUtils.isEmpty(userList)){
            throw new AuthException("用户名不存在，或密码不正确");
        }

        SysUserPo user = userList.get(0);
        String oldPwd = user.getPassword();

        if(!MD5Utils.comparison(pwd, oldPwd)){
            throw new AuthException("用户名不存在，或密码不正确");
        }

        //密码对比一致，装换为CurrentUserDetails对象
        CurrentUserDetails currentUserDetails = new CurrentUserDetails();
        BeanUtils.copyProperties(user, currentUserDetails);

        //生成用户token
        String accessToken = JwtUtils.createToken(currentUserDetails);

        //把token-用户信息放到redis
        redisTemplate.opsForValue().set("SYS-USER:SESSION:" + user.getUserName() + "_" + accessToken, currentUserDetails, 10, TimeUnit.MINUTES);

        UserLoginInfoVo result = new UserLoginInfoVo();
        result.setUserName(user.getUserName());
        result.setAccessToken(accessToken);

        return result;
    }
}
