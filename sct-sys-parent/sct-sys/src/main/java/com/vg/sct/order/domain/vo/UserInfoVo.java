package com.vg.sct.order.domain.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * @description: 用户信息
 * @author: xieweij
 * @create: 2021-02-24 11:35
 **/
@Getter
@Setter
public class UserInfoVo {

    private Integer id;

    private String wxId;

    private String userName;

    private String nickName;

    private String phoneNo;

    private String email;

    private Boolean isActive;
}
