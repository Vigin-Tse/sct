package com.vg.sct.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: 登录用户信息
 * @author: xieweij
 * @create: 2020-12-30 17:10
 **/
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CurrentUserDetails {

    private Integer id;

    private String wxId;

    private String userName;

    private String nickName;

    private String phoneNo;

    private String email;
}
