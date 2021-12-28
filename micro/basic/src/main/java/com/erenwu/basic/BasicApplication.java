package com.erenwu.basic;

import com.erenwu.common.BootstrapProfile;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: erenwu
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.erenwu.basic.feign")
@EnableSwagger2
@MapperScan("com.erenwu.basic.mapper")
public class BasicApplication {
    public static void main(String[] args) {
        BootstrapProfile.setBootstrapProfile();
        SpringApplication.run(BasicApplication.class, args);
    }
}
