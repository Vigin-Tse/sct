package com.vg.sct.user.dao.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:15
 */
@Getter
@Setter
public class SysUserModel {
    private Integer id;
    private String wxId;
    private String userName;
    private String nickName;
    private String password;
    private String phoneNo;
    private String email;
    private Integer creator;
    private Date createTime;
    private Integer modifier;
    private Date modifyTime;

}
