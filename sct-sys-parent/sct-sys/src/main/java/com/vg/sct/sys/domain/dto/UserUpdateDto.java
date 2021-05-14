package com.vg.sct.sys.domain.dto;

import lombok.Data;

@Data
public class UserUpdateDto {
    private Integer id;

    private String userName;

    private String nickName;

    private String phoneNo;

    private String email;

}
