package com.vg.sct.common.domain.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 用户名密码登录请求类
 * @author: xieweij
 * @create: 2020-12-30 17:05
 **/
@Setter
@Getter
public class UserPsLoginRquest {

    String userName;

    String password;
}
