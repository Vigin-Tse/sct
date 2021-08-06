package com.vg.sct.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EntityScan("com.vg.sct")
@EnableFeignClients(basePackages = "com.vg.sct")
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.vg.sct")
public class SctJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctJobApplication.class, args);
    }

}
