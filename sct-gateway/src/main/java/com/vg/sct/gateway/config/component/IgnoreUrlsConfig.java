package com.vg.sct.gateway.config.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: 网关白名单配置
 * @author: xieweij
 * @create: 2021-03-05 15:06
 **/
@Component
@ConfigurationProperties(prefix = "security.permited")
public class IgnoreUrlsConfig {

    private List<String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
