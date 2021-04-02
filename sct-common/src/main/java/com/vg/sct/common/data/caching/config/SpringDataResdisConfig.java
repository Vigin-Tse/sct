package com.vg.sct.common.data.caching.config;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @description: redis相关配置
 * @author: xieweij
 * @create: 2021-01-18 16:14
 **/

/**
 * @EnableCaching 当你在配置类(@ Configuration)上使用@EnableCaching注解时，会触发一个post processor，
 * 这会扫描每一个spring bean，查看是否已经存在注解对应的缓存。如果找到了，就会自动创建一个代理拦截方法调用，使用缓存的bean执行处理。
 */
//@Configuration
//@EnableCaching
public class SpringDataResdisConfig {

    /**
     * StringRedisTemplate
     * <p>
     * 默认采用的是String的序列化策略，
     * 保存的key和value都是采用此策略序列化保存
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        return stringRedisTemplate;
    }

    /**
     * RedisTemplate
     * redisTemplate.opsForValue();//操作字符串
     * redisTemplate.opsForHash();//操作hash
     * redisTemplate.opsForList();//操作list
     * redisTemplate.opsForSet();//操作set
     * redisTemplate.opsForZSet();//操作有序set
     * <p>
     * 序列化方式，spring-data-redis提供如下几种选择：
     * GenericToStringSerializer: 可以将任何对象泛化为字符串并序列化
     * Jackson2JsonRedisSerializer: 跟JacksonJsonRedisSerializer实际上是一样的
     * JacksonJsonRedisSerializer: 序列化object对象为json字符串
     * JdkSerializationRedisSerializer: 序列化java对象
     * StringRedisSerializer: 简单的字符串序列化
     */
    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());//设置redis key的序列化方式
//        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());// Object --> 序列化 --> 二进制流 --> redis-server存储
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        return redisTemplate;

    }

    /**
     * 配置spring cache注解功能
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        RedisCacheManager cacheManager = new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
        return cacheManager;
    }
}
