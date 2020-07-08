package com.vg.sct.user.dao.mapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vg.sct.user.dao.model.SysUserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description
 * @Author xieweij
 * @create 2020/7/8 16:31
 */
@SpringBootTest
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper userMapper;

    @Test
    public void testUserInsert(){
        SysUserModel user = new SysUserModel();
        user.setUserName("123456789");
        user.setNickName("柠檬精");
        user.setPassword("123456");
        user.setEmail("xxx@qq.com");

        this.userMapper.insert(user);
    }

    @Test
    public void testUserSelect(){
        SysUserModel user = this.userMapper.getOne(1);
        System.out.println(JSONObject.toJSONString(user));
    }
}
