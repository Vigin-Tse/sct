package com.vg.sct.user.service;

import com.vg.sct.user.domain.vo.UserLoginInfoVo;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:48
 */
public interface SysUserService {

    UserLoginInfoVo loginByUserNameAndPsw(String userName, String pwd);
}
