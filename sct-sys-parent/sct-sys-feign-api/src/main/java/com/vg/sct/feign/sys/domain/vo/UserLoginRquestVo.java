package com.vg.sct.feign.sys.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 用户登录成功返回信息
 * @author: xieweij
 * @create: 2020-12-31 09:28
 **/
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginRquestVo {

    private String userName;

    private String password;

}
