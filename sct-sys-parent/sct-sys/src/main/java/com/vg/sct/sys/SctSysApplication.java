package com.vg.sct.sys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EntityScan("com.vg.sct")
@EnableFeignClients(basePackages = "com.vg.sct")
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.vg.sct")
public class SctSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctSysApplication.class, args);
    }

}
