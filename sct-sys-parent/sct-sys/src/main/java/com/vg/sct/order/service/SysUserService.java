package com.vg.sct.order.service;

import com.vg.sct.common.support.http.HttpResponse;
import com.vg.sct.order.domain.dto.UserUpdateDto;
import com.vg.sct.order.domain.vo.UserInfoVo;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:48
 */
public interface SysUserService {

    HttpResponse loginByUserNameAndPsw(String userName, String pwd);

    UserInfoVo getUserInfo(Integer userId);

    /**
     * 后台-创建新用户
     * @param userUpdateDto
     * @return
     */
    UserUpdateDto createUser(UserUpdateDto userUpdateDto);

    /**
     * 后台-修改用户资料
     * @param userUpdateDto
     * @return
     */
    UserUpdateDto updateUser(UserUpdateDto userUpdateDto);
}
