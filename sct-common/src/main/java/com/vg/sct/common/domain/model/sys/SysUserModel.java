package com.vg.sct.common.domain.model.sys;

import com.vg.sct.common.domain.model.base.BaseModel;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:15
 */
@Data
@Entity
@Table(name = "sys_user")
public class SysUserModel extends BaseModel{

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

    @Column(name = "is_active")
    private Boolean isActive;

}
