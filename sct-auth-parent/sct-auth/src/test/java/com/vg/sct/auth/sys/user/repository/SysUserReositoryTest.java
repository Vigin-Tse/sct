package com.vg.sct.auth.sys.user.repository;

import com.alibaba.fastjson.JSON;
import com.vg.sct.common.domain.model.sys.SysUserModel;
import com.vg.sct.auth.repository.SysUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


/**
 * @description: 用户Jpa测试
 * @author: xieweij
 * @create: 2020-12-29 09:00
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysUserReositoryTest {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Test
    public void getUserByIdTest(){
        Optional<SysUserModel> oUser = this.sysUserRepository.findById(1);
        SysUserModel user = oUser.get();
        System.out.println(JSON.toJSONString(user));
    }


    @Test
    public void getUserByUserName(){
        System.out.println(JSON.toJSONString(this.sysUserRepository.findByUserName("leno")));
    }
}
