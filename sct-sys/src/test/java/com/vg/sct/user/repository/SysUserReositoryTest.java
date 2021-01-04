package com.vg.sct.user.repository;

import com.alibaba.fastjson.JSON;
import com.vg.sct.user.domain.po.SysUserPo;
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
        Optional<SysUserPo> oUser = this.sysUserRepository.findById(1);
        SysUserPo user = oUser.get();
        System.out.println(JSON.toJSONString(user));
    }
}
