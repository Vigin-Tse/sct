package com.vg.sct.order.redis;

import com.alibaba.fastjson.JSON;
import com.vg.sct.common.domain.model.sys.SysUserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @description: 用户rdis测试
 * @author: xieweij
 * @create: 2020-12-29 09:00
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysUserRedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    public void testUsingConnectionFactory() {

        RedisConnectionFactory s = redisConnectionFactory;
        System.out.println("当前使用的redis工厂:" + s.getConnection().getClientName());
    }

    @Test
    public void testSaveUser() {
        SysUserModel user = new SysUserModel();
        user.setUserName("小ming");
        user.setEmail("172946282@qq.com");
        user.setId(2);

        this.redisTemplate.opsForValue().set("sys-user:user:2", user);

        SysUserModel user2 = (SysUserModel) this.redisTemplate.opsForValue().get("sys-user:user:2");
        System.out.println(JSON.toJSONString(user2));
    }

    @Test
    public void testSaveString() {

        this.redisTemplate.opsForValue().set("1-1", "i am test");

        String value = (String) this.redisTemplate.opsForValue().get("1-1");
        System.out.println(value);
    }
}
