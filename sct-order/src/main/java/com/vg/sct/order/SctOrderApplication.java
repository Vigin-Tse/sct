package com.vg.sct.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author xieweij
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class SctOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctOrderApplication.class, args);
    }

}
