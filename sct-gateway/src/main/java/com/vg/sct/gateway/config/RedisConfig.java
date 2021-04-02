package com.vg.sct.gateway.config;

import com.vg.sct.common.data.caching.config.SpringDataResdisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Redis配置
 * @author: xieweij
 * @create: 2021-03-26 10:46
 **/
@Configuration
@EnableCaching
public class RedisConfig extends SpringDataResdisConfig {
}