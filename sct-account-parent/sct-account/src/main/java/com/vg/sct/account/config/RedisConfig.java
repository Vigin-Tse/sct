package com.vg.sct.account.config;

import com.vg.sct.common.support.config.caching.SpringDataResdisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * @description: redis配置
 * @author: xieweij
 * @create: 2021-03-26 10:17
 **/
@Configuration
@EnableCaching
public class RedisConfig extends SpringDataResdisConfig {
}
