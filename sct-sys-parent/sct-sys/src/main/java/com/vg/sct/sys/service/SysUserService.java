package com.vg.sct.sys.service;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.sys.domain.vo.UserInfoVo;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:48
 */
public interface SysUserService {

    HttpResponse loginByUserNameAndPsw(String userName, String pwd);

    UserInfoVo getUserInfo(Integer userId);
}
