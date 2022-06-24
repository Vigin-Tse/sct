package com.vg.sct.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @EntityScan：扫描JPA实体类的路径
 *
 * @EnableEurekaClient：标记此程序为为Eureka的客户端
 *
 * @EnableFeignClients：扫描Feign的路径
 *
 * @EnableJpaAuditing：开启jpa审计监听功能，回填特殊字段
 */
//@EnableEurekaClient
@EntityScan("com.vg.sct")
@EnableFeignClients(basePackages = "com.vg.sct")
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = "com.vg.sct")
public class SctProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(SctProductApplication.class, args);
    }

}
