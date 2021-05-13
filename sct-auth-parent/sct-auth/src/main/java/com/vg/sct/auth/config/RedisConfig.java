package com.vg.sct.auth.config;

import com.vg.sct.common.support.config.caching.SpringDataResdisConfig;
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
