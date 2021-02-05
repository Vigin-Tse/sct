package com.vg.sct.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.vg.sct")
public class SctAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctAuthApplication.class, args);
    }

}
