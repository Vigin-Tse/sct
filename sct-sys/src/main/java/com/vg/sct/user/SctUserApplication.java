package com.vg.sct.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SctUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctUserApplication.class, args);
    }

}
