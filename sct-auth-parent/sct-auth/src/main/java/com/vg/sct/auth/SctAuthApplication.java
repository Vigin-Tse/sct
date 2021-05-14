package com.vg.sct.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.vg.sct")
@SpringBootApplication
public class SctAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctAuthApplication.class, args);
    }

}
