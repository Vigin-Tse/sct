package com.vg.sct.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SctSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctSysApplication.class, args);
    }

}
