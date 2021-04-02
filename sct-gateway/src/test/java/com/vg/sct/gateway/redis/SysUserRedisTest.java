package com.vg.sct.gateway.redis;

import com.alibaba.fastjson.JSON;
import com.vg.sct.common.constants.redis.RedisNamespaceConstants;
import com.vg.sct.common.domain.po.sys.SysUserPo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @description: 用户rdis测试
 * @author: xieweij
 * @create: 2020-12-29 09:00
 **/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SysUserRedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    @Test
    public void testUsingConnectionFactory() {

        RedisConnectionFactory s = redisConnectionFactory;
        System.out.println("当前使用的redis工厂:" + s.getConnection().getClientName());
    }

    @Test
    public void testSaveUser() {
        SysUserPo user = new SysUserPo();
        user.setUserName("小ming");
        user.setEmail("172946282@qq.com");
        user.setId(3);

        this.redisTemplate.opsForValue().set("sys-user:user:3", user);

        SysUserPo user2 = (SysUserPo) this.redisTemplate.opsForValue().get("sys-user:user:3");
        System.out.println(JSON.toJSONString(user2));
    }

    @Test
    public void testSaveString() {

        this.redisTemplate.opsForValue().set("1-1", "i am test");

        String value = (String) this.redisTemplate.opsForValue().get("1-1");
        System.out.println(value);
    }

    @Test
    public void testGetToken(){
        int userId = 3;
        String redisToken = (String) this.redisTemplate.opsForValue().get(RedisNamespaceConstants.USER_LOGIN_TOKEN_NAMESPACE + userId);

//        String redisToken = (String) this.redisTemplate.opsForValue().get("USER:LOGIN:TOKEN:3");
        System.out.println("token=" + redisToken);

    }
}
