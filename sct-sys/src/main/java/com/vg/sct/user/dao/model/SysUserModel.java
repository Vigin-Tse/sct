package com.vg.sct.user.dao.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:15
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUserModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "wx_id")
    private String wxId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "email")
    private String email;

    @Column(name = "creator")
    private Integer creator;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "modifier")
    private Integer modifier;

    @Column(name = "modify_time")
    private Date modifyTime;

}
