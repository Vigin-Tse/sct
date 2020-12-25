package com.vg.sct.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author xieweij
 */
@SpringBootApplication
@EnableEurekaServer
public class SctEurekaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctEurekaServiceApplication.class, args);
    }

}
